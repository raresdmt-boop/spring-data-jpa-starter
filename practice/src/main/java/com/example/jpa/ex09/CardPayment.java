package com.example.jpa.ex09;

// TODO ex09.2: fă subtipul o entitate persistabilă. Adaugă:
//    @Entity
//    @DiscriminatorValue("CARD")
//  @DiscriminatorValue e valoarea scrisă în coloana discriminator pentru acest tip.
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
