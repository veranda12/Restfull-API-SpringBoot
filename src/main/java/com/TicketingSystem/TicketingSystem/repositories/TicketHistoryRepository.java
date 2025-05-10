package com.TicketingSystem.TicketingSystem.repositories;

import com.TicketingSystem.TicketingSystem.dtos.enumerasi.StatusTicket;
import com.TicketingSystem.TicketingSystem.models.Ticket;
import com.TicketingSystem.TicketingSystem.models.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, String> {


}
