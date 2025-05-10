package com.TicketingSystem.TicketingSystem.dtos.ticket;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import lombok.Data;

@Data
public class TicketUpdateDto {
    private final String id;
    private final StatusTicket statusTicket;

}
