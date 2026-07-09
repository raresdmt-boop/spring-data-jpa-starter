package com.example.jpa.ex06;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // TODO ex06.2: mapează partea "many" — Book deține relația (coloana FK author_id).
    //   Adaugă @ManyToOne (jakarta.persistence.ManyToOne) peste câmpul author.
    //   Fără mapare, Hibernate aruncă la pornire: "Could not determine type for: Author".
    @ManyToOne
    private Author author;

    protected Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
