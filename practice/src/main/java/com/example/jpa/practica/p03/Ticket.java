package com.example.jpa.practica.p03;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String status;

    protected Ticket() {
    }

    public Ticket(String subject, String status) {
        this.subject = subject;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }
}
