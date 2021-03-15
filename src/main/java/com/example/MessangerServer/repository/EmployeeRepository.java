package com.example.MessangerServer.repository;

import com.example.MessangerServer.dto.ContactsDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Поиск пользователя по Логину
    Employee findByUsername(String username);

    // Проверка на сущетсоввание логина
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Employee findByUserId(Long id);
}
