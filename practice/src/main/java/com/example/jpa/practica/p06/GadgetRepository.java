package com.example.jpa.practica.p06;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GadgetRepository extends JpaRepository<Gadget, Long> {

    @Query("select g from Gadget g where g.price between :min and :max order by g.price asc")
    List<Gadget> findByPriceRange(@Param("min") double min, @Param("max") double max);
}
