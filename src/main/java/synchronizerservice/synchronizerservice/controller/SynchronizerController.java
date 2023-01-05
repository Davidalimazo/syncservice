package synchronizerservice.synchronizerservice.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import synchronizerservice.synchronizerservice.entity.M_Mobile_Sync;
import synchronizerservice.synchronizerservice.model.RequestModel;
import synchronizerservice.synchronizerservice.service.SynchronizerService;

import java.util.List;

@Configuration
@Controller
public class SynchronizerController {
    private final SynchronizerService synchronizerService;

    public SynchronizerController(SynchronizerService synchronizerService) {
        this.synchronizerService = synchronizerService;
    }

    @Scheduled(fixedDelayString = "PT5H")
    public void writeRecordsIntoPmSync(){
            synchronizerService.prepersistRecordsInDB();
    }
    @Scheduled(fixedDelayString = "PT10S")
    public void saveAgentDetailsToSyncTableController(){
            synchronizerService.saveAgentDetailsToSyncTable();
    }
}
