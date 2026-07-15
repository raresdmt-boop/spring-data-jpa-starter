package com.example.jpa.provocari.c05;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Purchase> purchases = new ArrayList<>();

    protected Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setCustomer(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
