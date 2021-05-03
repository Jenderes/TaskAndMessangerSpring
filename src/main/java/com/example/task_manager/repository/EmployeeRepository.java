package com.example.task_manager.repository;

import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Поиск пользователя по Логину
    Employee findByUsername(String username);

    // Проверка на сущетсоввание логина
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Employee findByUserId(Long id);

    List<Employee> findEmployeesByDepartment(Department department);

    List<Employee> findEmployeesByDepartmentIsNull();
}
