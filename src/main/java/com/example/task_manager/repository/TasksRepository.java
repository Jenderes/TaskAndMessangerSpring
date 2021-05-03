package com.example.task_manager.repository;

import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Status;
import com.example.task_manager.model.Tasks;
import com.example.task_manager.model.WorkVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllByTaskForEmployees(Employee taskForEmployees);

    List<Tasks> findAllByTaskFromEmployees(Employee taskFromEmployees);

    Tasks findByTaskId(Long id);

    List<Tasks> findDistinctByTaskFromEmployeesAndTaskStatusAndWorkVariant(Employee taskFromEmployees, Status taskStatus, WorkVariant workVariant);
}
