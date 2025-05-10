package com.TicketingSystem.TicketingSystem.controllers;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketUpdateDto;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TickerInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketHeaderDto;
import com.TicketingSystem.TicketingSystem.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    private TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<TicketHeaderDto> findAllTicket(){
        return service.findAllTicket();
    }

//    @GetMapping("by-id/{ticketId}")
//    public List<TicketHeaderDto> findTicketById(@PathVariable String ticketId){
//        return service.findTicketById(ticketId);
//    }

    @GetMapping("find-by-year/{year}")
    public List<TicketHeaderDto> findTicketByYear(@PathVariable String year){
        return service.findTicketByYear(year);
    }
    @GetMapping("find-by-status/{status}")
    public List<TicketHeaderDto> findTicketByStatus(@PathVariable String status){
        return service.findTicketByStatus(status);
    }
    @GetMapping("find-by-urgency")
    public List<TicketHeaderDto> findTicketByUrgencyDesc(){
        return service.findTicketByUrgendyDesc();
    }

    @PostMapping("input")
    public ResponseEntity<List<TicketHeaderDto>> insertNewTicket(@RequestBody TickerInsertDto newTicket){
        return ResponseEntity.ok().body(
                service.insertTicket(newTicket)
        );
    }

    @PutMapping("ticket-update")
    public ResponseEntity<List<TicketHeaderDto>> UpdateTicketById(@RequestBody TicketUpdateDto newStatus){
        return ResponseEntity.ok().body(service.UpdateStatusTicket(newStatus));
    }
}
