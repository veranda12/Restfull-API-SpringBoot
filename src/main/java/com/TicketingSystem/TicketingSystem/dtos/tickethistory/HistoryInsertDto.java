package com.TicketingSystem.TicketingSystem.dtos.tickethistory;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusHistory;
import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.dtos.ticket.TicketHeaderDto;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.models.TicketHistory;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class HistoryInsertDto implements Serializable {
    private final String description;
    private final String completeDate;
    private final String approvedBy;


    public String getStatusTicketHistory(Ticket date){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("id","ID"));
        StatusHistory status = null;

        LocalDate dueDate = date.getDueDate();
        LocalDate complateDate = LocalDate.parse(completeDate, formatTanggal);

        if(complateDate.isAfter(dueDate)){
            status = StatusHistory.OVERDUE;
        }else{
            status = StatusHistory.ON_TIME;
        }

        return status.name();

    }

    public TicketHistory convert(Ticket dataTicket) {
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("id", "ID"));
        return new TicketHistory(
                dataTicket,
                description,
                LocalDate.parse(completeDate,formatTanggal),
                getStatusTicketHistory(dataTicket),
                approvedBy
        );
    }
}
