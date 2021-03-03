package com.example.MessangerServer.repository;

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

    Employee findByUserId(Long id);

    @Query(value = "SELECT employee.user_id, employee.first_name,employee.last_name FROM employee INNER JOIN contacts on employee.user_id = contacts.contact_user_id where employee.username <> ?1", nativeQuery = true)
    List<Object[]> getEmployeeListFullNameContact(String username);

}
