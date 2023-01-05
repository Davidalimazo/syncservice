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


    public ResponseEntity<?> saveAgentDetailsToSyncTable(){

        List<M_Mobile_Sync> agentFromPmSync = syncRepo.findAll();
        if(agentFromPmSync.size() < 1) return ResponseEntity.status(400).body("No records found");
        for (M_Mobile_Sync pmSynAgentDetails : agentFromPmSync){
            if(pmSynAgentDetails.getPmAgentId() == null && pmSynAgentDetails.getCardNum() == null){
                M_mobile_agent agentFromPm = m_mobile_agent_repository.findByPhoneNo(pmSynAgentDetails.getPmNum());
                if (agentFromPm != null){
                    pmSynAgentDetails.setPmAgentId(agentFromPm.getAgentId());
                    pmSynAgentDetails.setCardNum(agentFromPm.getCardNum());
                    syncRepo.saveAndFlush(pmSynAgentDetails);
                    List<Tms_Agent> agentFromTms = tmsAgentRepository.findByPmNumber(agentFromPm.getPhoneNo());
                    if(agentFromTms.size() > 0){
                        for (Tms_Agent tms : agentFromTms){
                            if(tms.getAgentCode() != agentFromPm.getAgentId()){
                                tms.setAgentCode(agentFromPm.getAgentId());
                                tmsAgentRepository.saveAndFlush(tms);
                            }
                        }

                    }
                }
            }
        }
        return ResponseEntity.status(200).body("Records updated successfully");
    }

    public ResponseEntity<?> prepersistRecordsInDB(){

        List <Tms_Agent> getAllAgentsFromTms = tmsAgentRepository.findAll();
        if(getAllAgentsFromTms.size() < 1) return null;
        M_Mobile_Sync pmSync = new M_Mobile_Sync();
        for (Tms_Agent db : getAllAgentsFromTms){
                M_Mobile_Sync agentFromSync = syncRepo.findByPmNum(db.getPmNumber());
                if(agentFromSync == null){
                    pmSync.setPmNum(db.getPmNumber());
                    pmSync.setTmsAgentId(db.getId());
                syncRepo.save(pmSync);
            }
        }
        return ResponseEntity.ok("ok");
    }

}
