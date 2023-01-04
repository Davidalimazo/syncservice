package synchronizerservice.synchronizerservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.model.RequestModel;
import synchronizerservice.synchronizerservice.service.SynchronizerService;

import java.util.List;

@RestController
@RequestMapping("/api/pmsync")
public class SynchronizerController {
    private final SynchronizerService synchronizerService;

    public SynchronizerController(SynchronizerService synchronizerService) {
        this.synchronizerService = synchronizerService;
    }
    @PostMapping
    public ResponseEntity<?> saveAgentDetails(@RequestBody RequestModel requestModel){
        return synchronizerService.saveAgentDetailsToSyncTable(requestModel);
    }

    @Scheduled(fixedDelayString = "PT23H")
    public void updateAgentPhone(){
        synchronizerService.updatePreviousRecords();
    }

}
