package com.example.jpa.practica.p10;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    // Cerință: șterge toate job-urile cu status-ul dat și întoarce câte au fost
    // șterse. Derived delete: prefix `deleteBy`, retur `long`.\\

    long deleteByStatus(String status);

//    default long deleteByStatus(String status) {
//        throw new UnsupportedOperationException("TODO p10");
//    }
}
