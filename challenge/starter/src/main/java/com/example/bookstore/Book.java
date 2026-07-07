package com.example.bookstore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double price;

    // TODO challenge.2: partea "many" — Book deține relația (coloana FK category_id).
    //   Adaugă @ManyToOne (jakarta.persistence.ManyToOne) peste câmpul category.
    private Category category;

    protected Book() {
    }

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
