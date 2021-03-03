package com.example.MessangerServer.repository;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllByTaskForEmployees(Employee taskForEmployees);

    List<Tasks> findAllByTaskFromEmployees(Employee taskFromEmployees);

    Tasks findByTaskId(Long id);
}
