package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees,Long> {
    Employees findByUsername(String username);
}
