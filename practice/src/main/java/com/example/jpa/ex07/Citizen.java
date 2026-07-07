package com.example.jpa.ex07;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO ex07: mapează relația 1—1 Citizen ─ Passport. Citizen deține relația
    //  (coloana FK passport_id). Adaugă:
    //    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    //    @JoinColumn(name = "passport_id")
    //  Best practice: partea care deține FK primește @JoinColumn; cascade ALL
    //  salvează pașaportul odată cu cetățeanul.
    private Passport passport;

    protected Citizen() {
    }

    public Citizen(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
