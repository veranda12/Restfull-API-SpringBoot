package com.TicketingSystem.TicketingSystem.services;

import com.TicketingSystem.TicketingSystem.dtos.employee.EmployeeInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TickerInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketHeaderDto;
import com.TicketingSystem.TicketingSystem.dtos.tickethistory.HistoryInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.tickethistory.TicketHistoryHeaderDto;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.models.TicketHistory;
import com.TicketingSystem.TicketingSystem.repositories.TicketHistoryRepository;
import com.TicketingSystem.TicketingSystem.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TicketHistoryService {
    private TicketHistoryRepository ticketHistoryRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketHistoryService(TicketHistoryRepository ticketHistoryRepository, TicketRepository ticketRepository) {
        this.ticketHistoryRepository = ticketHistoryRepository;
        this.ticketRepository = ticketRepository;
    }

//    public List<TicketHistoryHeaderDto> findAllTicketHistory(){
//        return TicketHistoryHeaderDto.toList(ticketHistoryRepository.FindAllTicketHistory());
//    }

    public List<TicketHistoryHeaderDto> InsertHistory(String idTicket, HistoryInsertDto newHistory){
        Ticket cekIdTicket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new EntityNotFoundException("Ticket tidak ditemukan"));

        Ticket ticketData = ticketRepository.getById(cekIdTicket.getId());
        TicketHistory ticketHistory = newHistory.convert(ticketData);

        if (cekIdTicket.getStatus().equals("CENCELED")){
            throw new EntityNotFoundException("tidak dapat menginput history");
        }

        ticketHistoryRepository.save(ticketHistory);

        ticketData.setStatus(StatusTicket.COMPLETED.name());
        ticketRepository.save(ticketData);

        return TicketHistoryHeaderDto.toList(ticketHistoryRepository.findAll());
    }



}
