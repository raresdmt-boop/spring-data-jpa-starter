package com.example.demo.mostenire;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARD")
public class CardPayment extends Payment {

    private String cardNumber;

    protected CardPayment() {
    }

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String describe() {
        return "CARD " + cardNumber;
    }
}
