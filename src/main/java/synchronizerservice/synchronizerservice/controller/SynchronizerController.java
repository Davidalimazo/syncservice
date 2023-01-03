package synchronizerservice.synchronizerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.model.RequestModel;
import synchronizerservice.synchronizerservice.service.SynchronizerService;

import java.util.List;

@RestController
@RequestMapping("/api/synchronizer")
public class SynchronizerController {
    private final SynchronizerService synchronizerService;

    public SynchronizerController(SynchronizerService synchronizerService) {
        this.synchronizerService = synchronizerService;
    }
    @GetMapping
    public List getAllAgents(){
        return synchronizerService.getAllAgents();
    }

    @Scheduled(fixedDelayString = "PT20S")
    public ResponseEntity<?> saveAgentDetails(){
        return synchronizerService.saveAgentDetails();
    }

}
