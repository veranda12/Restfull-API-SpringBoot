package com.TicketingSystem.TicketingSystem.dtos.ticket;

import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class TicketHeaderDto {
    private final String id;
    private final String tittle;
    private final String detail;
    private final String requestDate;
    private final String dueDate;
    private final String urgency;
    private final String createdById;
    private final String appointedToId;
    private final String status;
    
    public static TicketHeaderDto set(Ticket ticket){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id","ID"));
        DateTimeFormatter formatRequsetDate = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm:ss", new Locale("id","ID"));
        return new TicketHeaderDto(
                ticket.getId(),
                ticket.getTittle(),
                ticket.getDetails(),
                ticket.getRequestDate().format(formatRequsetDate),
                ticket.getDueDate().format(formatDate),
                ticket.getUrgency(),
                ticket.getCreatedBy().getId(),
                ticket.getAppointedTo().getId(),
                ticket.getStatus());
    }
    
    public static List<TicketHeaderDto> toList(List<Ticket> ticketList){
        List<TicketHeaderDto> result = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            result.add(set(ticket));
        }
        return result;
    }
}
