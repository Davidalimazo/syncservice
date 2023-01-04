package synchronizerservice.synchronizerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.entity.M_mobile_agent;
import synchronizerservice.synchronizerservice.entity.Tms_Agent;
import synchronizerservice.synchronizerservice.model.RequestModel;
import synchronizerservice.synchronizerservice.repository.*;

import java.util.HashMap;
import java.util.List;

@Service
public class SynchronizerService {
    private final SyncRepo syncRepo;
    private final M_mobile_agent_repository m_mobile_agent_repository;
    private final Tms_Agent_Repository tmsAgentRepository;

    @Autowired
    RestTemplate restTemplate;

    public SynchronizerService(SyncRepo syncRepo, M_mobile_agent_repository m_mobile_agent_repository,
                               Tms_Agent_Repository tmsAgentRepository) {
        this.syncRepo = syncRepo;
        this.m_mobile_agent_repository = m_mobile_agent_repository;
        this.tmsAgentRepository = tmsAgentRepository;
    }


    public ResponseEntity<?> saveAgentDetailsToSyncTable(RequestModel requestModel){;
        M_mobile_agent agent = m_mobile_agent_repository.findByPhoneNo(requestModel.getPmNumber());
        M_Mobile_Sync doesRecordExist = syncRepo.findByPmNum(requestModel.getPmNumber());

        if(agent != null && doesRecordExist == null){
            M_Mobile_Sync pmSych = new M_Mobile_Sync();
            pmSych.setCardNum(agent.getCardNum());
            pmSych.setPmAgentId(agent.getAgentId());
            pmSych.setTmsAgentId(requestModel.getTmsAgentId());
            pmSych.setPmNum(agent.getPhoneNo());
            syncRepo.save(pmSych);
            Tms_Agent agentCode = tmsAgentRepository.findByPmNumber(agent.getPhoneNo());
            if(agentCode.getAgentCode() != agent.getAgentId()){
                agentCode.setAgentCode(agent.getAgentId());
                tmsAgentRepository.saveAndFlush(agentCode);
            }
            return ResponseEntity.ok("Agent details updated successfully");
        }
        return ResponseEntity.status(200).body("agent details already exist in pmSync service");

    }


    public void updatePreviousRecords(){
        List<Tms_Agent> agentsFromTms = tmsAgentRepository.findAll();
        M_Mobile_Sync pmSync = new M_Mobile_Sync();
        if (agentsFromTms.size() < 1) {
            System.out.println("No agents found");
            return;
        };

        for (Tms_Agent agent : agentsFromTms){
            if(agent.getApproval() == true){
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBasicAuth("user", "pass");
                String url = "http://localhost:8080/api/pmsync";
                HashMap<String, Object> requestJson = new HashMap<>();
                requestJson.put("pmNumber", agent.getPmNumber());
                requestJson.put("tmsAgentId", agent.getId());
                HttpEntity<HashMap> entity = new HttpEntity<HashMap>(requestJson, headers);
                String response = restTemplate.postForObject(url, entity, String.class);
                System.out.println(response);
            }
        }

    }


}
