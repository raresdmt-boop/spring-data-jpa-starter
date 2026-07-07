package com.example.jpa.ex09;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// TODO ex09.1: declară strategia de moștenire pe clasa de bază. Adaugă:
//    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//    @DiscriminatorColumn(name = "payment_type")
//  SINGLE_TABLE = toate subtipurile într-un singur tabel, deosebite printr-o
//  coloană discriminator. E strategia implicită și cea mai rapidă (fără JOIN-uri).
@Entity
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    protected Payment() {
    }

    protected Payment(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String describe();
}
