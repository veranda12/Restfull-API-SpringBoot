package com.TicketingSystem.TicketingSystem.dtos.ticket;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.repositories.TicketRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class TickerInsertDto {
    private final String title;
    private final String details;
    private final LocalDateTime requestDate = LocalDateTime.now();
    private final String dueDate;
    private final String urgency;
    private final String createdById;
    private final String appointedToId;
    private final StatusTicket statusTiket;

    private final TicketRepository ticketRepository;



    private String newTicketId(Ticket lastTicketId) {
        String ticketId = lastTicketId.getId();
        String tahun = String.valueOf(LocalDate.now().getYear());


        String lastTahun = ticketId.substring(4,8);
        Integer digitLast = Integer.valueOf(ticketId.substring(9));
        Integer digit = digitLast + 1;

        String hasil;
        if (tahun.equals(lastTahun)) {
            tahun = lastTahun;
            hasil = "SRQ/" + tahun + "/" + digit;
        } else {
            hasil = "SRQ/" + tahun + "/" + 1;
        }

        return hasil;
    }

    public Ticket convert(Ticket lastId) {
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("id", "ID"));
        return new Ticket(
                newTicketId(lastId),
                title,
                details,
                requestDate,
                LocalDate.parse(dueDate, formatTanggal),
                urgency,
                createdById,
                appointedToId,
                StatusTicket.IN_PROGRESS.name()
        );
    }
}
