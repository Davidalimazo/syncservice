package synchronizerservice.synchronizerservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import synchronizerservice.synchronizerservice.entity.Agent_Account;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.entity.M_mobile_agent;
import synchronizerservice.synchronizerservice.entity.Tms_Agent;
import synchronizerservice.synchronizerservice.repository.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service

public class SynchronizerService {
    private final SyncRepo syncRepo;
    private final Agent_Account_Repository agentAccountRepository;
    private final M_mobile_agent_repository m_mobile_agent_repository;
    private final Tms_Agent_Repository tmsAgentRepository;
    @Value("${secretKy}")
    private String value;
    @Value("${initializationVector}")
    private String initializationVector;

    @Autowired
    RestTemplate restTemplate;

    public SynchronizerService(SyncRepo syncRepo, Agent_Account_Repository agentAccountRepository, M_mobile_agent_repository m_mobile_agent_repository,
                               Tms_Agent_Repository tmsAgentRepository) {
        this.syncRepo = syncRepo;
        this.agentAccountRepository = agentAccountRepository;
        this.m_mobile_agent_repository = m_mobile_agent_repository;
        this.tmsAgentRepository = tmsAgentRepository;
    }


public ResponseEntity<?> saveAgentDetailsToSyncTable() {

//TODO: get all agent from pmSync without pmAgentId and cardNumber
    List<M_Mobile_Sync> agentFromPmSync = syncRepo.findAgentWithoutPmNumAndCardNum();

    if (agentFromPmSync.size() > 0){
        for (M_Mobile_Sync isEligibleForUpdate:agentFromPmSync){
            //TODO: fetch tms agents whose cardNumber and pmAgentId have been updated from pmSync with pmSync TmsAgentId
            List<M_Mobile_Sync> tmsAgentAlreadyUpdated = syncRepo.findTmsAgentsWhoseIdAndCardNumAreNotNull(isEligibleForUpdate.getTmsAgentId());
            //TODO: fetch agent cardNumber and pmAgentId from pmTable with pmSync pmNumber
            M_mobile_agent agentFromPm = m_mobile_agent_repository.findByPhoneNo(isEligibleForUpdate.getPmNum());
            if(tmsAgentAlreadyUpdated.size() > 0){
                if(agentFromPm != null){
                    isEligibleForUpdate.setPmAgentId(agentFromPm.getAgentId());
                    isEligibleForUpdate.setCardNum(agentFromPm.getCardNum());
                    //TODO: update agent cardNumber and pmAgentId in the pmSync table
                    syncRepo.saveAndFlush(isEligibleForUpdate);
                    log.info( agentFromPm.getAgentName()+ " records has being updated on pmSync table");
                }
            }else {
                if(agentFromPm != null){
                    isEligibleForUpdate.setPmAgentId(agentFromPm.getAgentId());
                    isEligibleForUpdate.setCardNum(agentFromPm.getCardNum());
                    //TODO: update agent cardNumber and pmAgentId in the pmSync table
                    syncRepo.saveAndFlush(isEligibleForUpdate);
                    log.info( agentFromPm.getAgentName()+ " records has being updated on pmSync table");
                    //TODO: fetch agent tms details from the agent table
                    Tms_Agent tms = tmsAgentRepository.findByAgentId(isEligibleForUpdate.getTmsAgentId());
                    //TODO: check if the agentCode on tms  is the same as the one on pm
                    if(tms.getAgentCode() != agentFromPm.getAgentId()){
                        //TODO: if different update the agentCode on tms
                        tms.setAgentCode(agentFromPm.getAgentId());
                        tmsAgentRepository.saveAndFlush(tms);
                        log.info( agentFromPm.getAgentName()+ " agent code has being updated on tms agent table");
                    }
                }

            }


        }
    }
    return ResponseEntity.status(200).body("Records updated successfully");
}

/*
    public ResponseEntity<?> prepersistRecordsInDB(){

      List<Tms_Agent> tms_agentList = tmsAgentRepository.findAll();

      for (Tms_Agent tms:tms_agentList){
         List<Agent_Account> ag = agentAccountRepository.findByAgentId(tms.getId());
         if(ag != null){
             ag.stream().forEach(i->{
                 M_Mobile_Sync pmSync = new M_Mobile_Sync();
                 pmSync.setTmsAgentId(tms.getId());
                 pmSync.setPmNum(i.getAccountNumber());
                 syncRepo.save(pmSync);
                 System.out.println(i.getAccountName()+" added");
             });
         }
      }
        return ResponseEntity.ok("ok");
    }
 */
}
