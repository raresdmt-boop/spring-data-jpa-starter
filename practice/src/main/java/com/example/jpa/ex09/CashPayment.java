package com.example.jpa.ex09;

// TODO ex09.3: la fel ca la CardPayment — adaugă @Entity și @DiscriminatorValue("CASH").
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
