package com.TicketingSystem.TicketingSystem.dtos.tickethistory;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusHistory;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketHeaderDto;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.models.TicketHistory;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TicketHistoryHeaderDto {
    private final String id;
    private final String description;
    private final LocalDate completeDate;
    private final String status;
    private final String approvedBy;

    private static TicketHistoryHeaderDto set(TicketHistory ticket){
        return new TicketHistoryHeaderDto(
                ticket.getId(),
                ticket.getDeskription(),
                ticket.getCompletedDate(),
                ticket.getStatus(),
                ticket.getApprovedBy().getId()
        );
    }

    public static List<TicketHistoryHeaderDto> toList(List<TicketHistory> ticketList){
        List<TicketHistoryHeaderDto> result = new ArrayList<>();
        for (TicketHistory ticket : ticketList) {
            result.add(set(ticket));
        }
        return result;
    }
}
