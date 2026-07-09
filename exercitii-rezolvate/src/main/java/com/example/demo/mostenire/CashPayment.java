package com.example.demo.mostenire;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

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
