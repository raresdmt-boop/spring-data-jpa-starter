package com.example.jpa.provocari.c07;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("select avg(w.salary) from Worker w where w.department  = :department")
    Double averageSalary(@Param("department") String department) ;
}
