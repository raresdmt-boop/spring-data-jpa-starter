package com.example.jpa.practica.p03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Cerință: tichetele al căror status e în lista dată (cuvânt cheie `In`).

    List<Ticket> findByStatusIn(List<String> statuses);

//    default List<Ticket> findByStatusIn(List<String> statuses) {
//        throw new UnsupportedOperationException("TODO p03.1");
//    }

    // Cerință: câte tichete au un anumit status.

    long countByStatus(String status);

//    default long countByStatus(String status) {
//        throw new UnsupportedOperationException("TODO p03.2");
//    }
}
