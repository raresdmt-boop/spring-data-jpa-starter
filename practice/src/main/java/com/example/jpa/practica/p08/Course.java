package com.example.jpa.practica.p08;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String category;

    private int enrolled;

    protected Course() {
    }

    public Course(String title, String category, int enrolled) {
        this.title = title;
        this.category = category;
        this.enrolled = enrolled;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getEnrolled() {
        return enrolled;
    }
}
