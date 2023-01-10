package synchronizerservice.synchronizerservice.controller;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import synchronizerservice.synchronizerservice.service.SynchronizerService;

import java.util.List;

@Configuration
@Controller
public class SynchronizerController {
    private final SynchronizerService synchronizerService;

    public SynchronizerController(SynchronizerService synchronizerService) {
        this.synchronizerService = synchronizerService;
    }
/*
 @EventListener(ApplicationReadyEvent.class)
    public void writeRecordsIntoPmSync() {
        synchronizerService.prepersistRecordsInDB();
    }
    */
    @Scheduled(fixedDelayString = "PT10S")
    public void upDateTmsAgentRecords() {
        synchronizerService.saveAgentDetailsToSyncTable();
    }
}
