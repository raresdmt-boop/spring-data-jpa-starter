package com.example.jpa.practica.p03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatusIn(List<String> statuses);

    long countByStatus(String status);
}
