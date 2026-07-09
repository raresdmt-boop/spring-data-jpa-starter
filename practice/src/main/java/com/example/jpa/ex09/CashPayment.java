package com.example.jpa.ex09;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// TODO ex09.3: la fel ca la CardPayment — adaugă @Entity și @DiscriminatorValue("CASH").
@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    protected CashPayment() {
    }

    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public String describe() {
        return "CASH";
    }
}
