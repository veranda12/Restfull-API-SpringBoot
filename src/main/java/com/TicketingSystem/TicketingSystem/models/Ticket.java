package com.TicketingSystem.TicketingSystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @Column(name = "TicketID")
    private String id;

    @Column(name = "Title")
    private String tittle;

    @Column(name = "Details")
    private String details;

    @Column(name = "RequestDate")
    private LocalDateTime requestDate;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "Urgency")
    private String urgency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CreatedBy", nullable = false)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AppointedTo", nullable = false)
    private Employee appointedTo;

    @Column(name = "Status", nullable = true, length = 11)
    private String status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket")
    private TicketHistory ticketHistory;

    public Ticket(String id, String tittle, String details, LocalDateTime requestDate, LocalDate dueDate, String urgency, String createdBy, String appointedTo, String status) {
        this.id = id;
        this.tittle = tittle;
        this.details = details;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.urgency = urgency;
        this.createdBy = new Employee(createdBy);
        this.appointedTo = new Employee(appointedTo);
        this.status = status;
    }
}
