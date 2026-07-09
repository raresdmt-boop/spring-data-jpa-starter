package com.example.demo.mostenire;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.DemoExercise;

@Component
public class MostenireExercise implements DemoExercise {

    private final PaymentRepository payments;

    public MostenireExercise(PaymentRepository payments) {
        this.payments = payments;
    }

    @Override
    public String key() {
        return "mostenire";
    }

    @Override
    public String title() {
        return "@Inheritance SINGLE_TABLE: Payment -> CardPayment / CashPayment intr-un singur tabel";
    }

    @Override
    public void run() {
        payments.deleteAll();

        payments.save(new CardPayment(250.0, "1234-5678-9012-3456"));
        payments.save(new CardPayment(99.5, "9999-0000-1111-2222"));
        payments.save(new CashPayment(40.0));

        System.out.println("Toate subtipurile stau in acelasi tabel, deosebite prin coloana discriminator payment_type.");
        System.out.println("Plati in DB: " + payments.count());

        System.out.println();
        System.out.println("--- repository peste tipul de baza intoarce POLIMORFIC ---");
        List<Payment> all = payments.findAll();
        all.forEach(p -> System.out.println(
                "  " + p.getClass().getSimpleName() + " | " + p.getAmount() + " lei | describe(): " + p.describe()));

        double total = all.stream().mapToDouble(Payment::getAmount).sum();
        System.out.println();
        System.out.println("Total incasat: " + total + " lei");

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT payment_type, amount, card_number FROM payment;  (vezi coloana discriminator)");
    }
}
