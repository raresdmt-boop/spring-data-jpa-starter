package com.example.jpa.practica.p09;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    // Cerință: abonații cu `active` = valoarea dată, sortați după parametrul Sort
    // primit (nu din numele metodei). Metodă derivată cu al doilea parametru Sort.
    default List<Subscriber> findByActive(boolean active, Sort sort) {
        throw new UnsupportedOperationException("TODO p09");
    }
}
