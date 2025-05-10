package com.TicketingSystem.TicketingSystem.models;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TicketHistory")
@Getter
@Setter
@NoArgsConstructor
public class TicketHistory {

    @Id
    @Column(name = "TicketID", nullable = true, length = 20)
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "TicketID", nullable = false)
    private Ticket ticket;

    @Column(name = "Description")
    private String deskription;

    @Column(name = "CompletedDate")
    private LocalDate completedDate;

    @Column(name = "Status", nullable = false, length = 7)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ApprovedBy", nullable = false)
    private Employee ApprovedBy;

//    public TicketHistory(String id, String deskription, LocalDate completedDate, String status, String approvedBy) {
//        this.id = id;
//        this.deskription = deskription;
//        this.completedDate = completedDate;
//        this.status = status;
//        this.ApprovedBy = new Employee(approvedBy);
//    }

    public TicketHistory(Ticket ticket, String deskription, LocalDate completedDate, String status, String approvedBy) {
        this.ticket = ticket;
        this.deskription = deskription;
        this.completedDate = completedDate;
        this.status = status;
        ApprovedBy = new Employee(approvedBy);
    }
}
