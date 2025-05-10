package com.TicketingSystem.TicketingSystem.controllers;

import com.TicketingSystem.TicketingSystem.dtos.tickethistory.HistoryInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.tickethistory.TicketHistoryHeaderDto;
import com.TicketingSystem.TicketingSystem.models.TicketHistory;
import com.TicketingSystem.TicketingSystem.services.TicketHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class TicketHistoryController {
    private TicketHistoryService service;

    @Autowired
    public TicketHistoryController(TicketHistoryService service) {
        this.service = service;
    }
    
//    @GetMapping
//    public List<TicketHistoryHeaderDto> findAllHistory(){
//        return service.findAllTicketHistory();
//    }

    @PutMapping("input-history")
    public ResponseEntity<List<TicketHistoryHeaderDto>> insertHistory(@RequestParam String ticketId, @RequestBody HistoryInsertDto
            newHistory){
        return ResponseEntity.ok().body(service.InsertHistory(ticketId,newHistory));
    }
}
