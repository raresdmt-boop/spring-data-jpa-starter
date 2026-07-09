package com.example.demo.derivate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentOrderBySalaryDesc(String department);

    List<Employee> findByDepartmentAndActiveTrue(String department);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByLastNameContainingIgnoreCase(String fragment);

    List<Employee> findTop3ByOrderBySalaryDesc();

    long countByActiveTrue();

    boolean existsByEmail(String email);
}
