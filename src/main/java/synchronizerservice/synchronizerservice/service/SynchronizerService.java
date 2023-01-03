package synchronizerservice.synchronizerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.entity.M_mobile_agent;
import synchronizerservice.synchronizerservice.entity.M_mobile_subscriber;
import synchronizerservice.synchronizerservice.repository.M_mobile_agent_repository;
import synchronizerservice.synchronizerservice.repository.M_mobile_subscriber_card_repository;
import synchronizerservice.synchronizerservice.repository.M_mobile_subscriber_repository;
import synchronizerservice.synchronizerservice.repository.SyncRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SynchronizerService {
    private final SyncRepo syncRepo;
    private final M_mobile_agent_repository m_mobile_agent_repository;
    private final M_mobile_subscriber_card_repository m_mobile_subscriber_card_repository;
    private final M_mobile_subscriber_repository m_mobile_subscriber_repository;

    @Autowired
    RestTemplate restTemplate;

    public SynchronizerService(SyncRepo syncRepo, M_mobile_agent_repository m_mobile_agent_repository,
                               M_mobile_subscriber_card_repository m_mobile_subscriber_card_repository,
                               M_mobile_subscriber_repository m_mobile_subscriber_repository) {
        this.syncRepo = syncRepo;
        this.m_mobile_agent_repository = m_mobile_agent_repository;
        this.m_mobile_subscriber_card_repository = m_mobile_subscriber_card_repository;
        this.m_mobile_subscriber_repository = m_mobile_subscriber_repository;
    }


    public List getAllAgents(){
        return syncRepo.findAll();
    }

    public ResponseEntity<?> saveAgentDetails(){

        List <M_mobile_agent> getAllAgentsFromSync = m_mobile_agent_repository.findAll();
        if(getAllAgentsFromSync.size() < 0) return null;

        for (M_mobile_agent db : getAllAgentsFromSync){
            if(db.getApprove() == false){
                M_Mobile_Sync agentFromSync = syncRepo.findByPmNumber(db.getPhoneNo());
                agentFromSync.setAgentCode(db.getAgentId());
                agentFromSync.setCardNumber(db.getCardNum());
                syncRepo.saveAndFlush(agentFromSync);
            }
        }
        return ResponseEntity.ok("ok");
    }
}
