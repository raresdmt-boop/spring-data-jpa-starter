package com.example.jpa.ex01;

// TODO ex01: transformă acest POJO într-o entitate JPA.
//  1. Adaugă @Entity peste clasă (jakarta.persistence.Entity).
//  2. Adaugă @Id peste câmpul id.
//  3. Adaugă @GeneratedValue(strategy = GenerationType.IDENTITY) ca baza de date
//     să genereze automat cheia primară la insert.
// Fără @Entity, Spring aruncă la pornire: "Not a managed type: Product".

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    protected Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
