package com.TicketingSystem.TicketingSystem.services;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketUpdateDto;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TickerInsertDto;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketHeaderDto;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketHeaderDto> findAllTicket() {
        return TicketHeaderDto.toList(ticketRepository.FindAllTicket());
    }

//    public List<TicketHeaderDto> findTicketById(String ticketId){
//        return TicketHeaderDto.toList(ticketRepository.FindById(ticketId));
//    }

    public List<TicketHeaderDto> insertTicket(TickerInsertDto newTicket) {
        Ticket lastId = ticketRepository.getLastTicketId();
        Ticket ticket = newTicket.convert(lastId);
        ticketRepository.save(ticket);
        return TicketHeaderDto.toList(ticketRepository.FindAllTicket());
    }

    public List<TicketHeaderDto> UpdateStatusTicket(TicketUpdateDto statusTicket) {
        String idTicket = statusTicket.getId();

        Ticket oldTicket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new EntityNotFoundException("ticket tidak ditemukan"));


        oldTicket.setStatus(statusTicket.getStatusTicket() == null ? oldTicket.getStatus() : statusTicket.getStatusTicket().name());

        ticketRepository.save(oldTicket);

        return TicketHeaderDto.toList(ticketRepository.FindAllTicket());
    }

    public List<TicketHeaderDto> findTicketByYear(String year) {


        return TicketHeaderDto.toList(ticketRepository.findByYear(year));
    }

    public List<TicketHeaderDto> findTicketByStatus(String status) {

        if (status.equals("progress") || status.equals("Progress") || status.equals("PROGRESS")) {
            status = String.valueOf(StatusTicket.IN_PROGRESS);
        } else if (status.equals("complete") || status.equals("COMPLETE")) {
            status = String.valueOf(StatusTicket.COMPLETED);
        } else if (status.equals("cencel") || status.equals("CENCEL")) {
            status = String.valueOf(StatusTicket.CENCELED);
        } else {
            throw new EntityNotFoundException("ticket tidak ditemukan");
        }
        return TicketHeaderDto.toList(ticketRepository.findByStatus(status.toUpperCase()));
    }

    public List<TicketHeaderDto> findTicketByUrgendyDesc() {
        return TicketHeaderDto.toList(ticketRepository.findByOrderByUrgencyDesc());
    }
}
