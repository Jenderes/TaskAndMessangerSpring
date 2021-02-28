package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Поиск пользователя по Логину
    Employee findByUsername(String username);

    // Проверка на сущетсоввание логина
    Boolean existsByUsername(String username);

    Employee findByUserId(Long id);

}
