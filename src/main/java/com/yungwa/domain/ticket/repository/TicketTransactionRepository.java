package com.yungwa.domain.ticket.repository;

import com.yungwa.domain.ticket.entity.TicketTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTransactionRepository extends JpaRepository<TicketTransaction, Long> {
}
