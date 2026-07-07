package com.example.jpa.ex02;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int priority;

    private boolean done;

    protected Task() {
    }

    public Task(String title, int priority, boolean done) {
        this.title = title;
        this.priority = priority;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }
}
