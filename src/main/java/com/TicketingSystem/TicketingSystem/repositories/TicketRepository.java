package com.TicketingSystem.TicketingSystem.repositories;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.models.Employee;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(value = """
            select TOP 1 * from Ticket order by RequestDate desc, TicketID desc
            """, nativeQuery = true)
    Ticket getLastTicketId();

    @Query(value = """
            select * from Ticket
            order by SUBSTRING(TicketID,10,100) desc
            """, nativeQuery = true)
    List<Ticket> FindAllTicket();

    //SQR/2022/1
    @Query(value = """
            select * from Ticket
            WHERE TicketID = :ticketId;
            """, nativeQuery = true)
    List<Ticket> FindById(@Param("ticketId") String ticketId);

    @Query(value = """
            select * from Ticket where TicketID = :ticketID
            """, nativeQuery = true)
    Ticket getDate(@Param("ticketID") String ticketID);


    @Query(value = """
            select * from Ticket where DueDate LIKE CONCAT('%', :year,'%')
            """, nativeQuery = true)
    List<Ticket> findByYear(@Param("year") String year);


    @Query(value = """
            select * from Ticket where Status = :status
            """, nativeQuery = true)
    List<Ticket> findByStatus(@Param("status") String status);


    @Query(value = """
            select * from Ticket order by Urgency, SUBSTRING(TicketID, 10,1)  desc
            """, nativeQuery = true)
    List<Ticket> findByOrderByUrgencyDesc();
}
