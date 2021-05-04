package com.example.task_manager.repository;

import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Status;
import com.example.task_manager.model.Tasks;
import com.example.task_manager.model.WorkVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllBySenderEmployee(Employee senderEmployee);

    List<Tasks> findAllByRecipientEmployee(Employee recipientEmployee);

    Tasks findByTaskId(Long id);

    List<Tasks> findAllBySenderEmployeeAndTaskStatusAndWorkVariant(Employee taskFromEmployees, Status taskStatus, WorkVariant workVariant);
}
