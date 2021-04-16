package com.example.MessangerServer.service;

import com.example.MessangerServer.dto.EmployeeTaskDto;
import com.example.MessangerServer.dto.StatisticDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.model.Tasks;
import com.example.MessangerServer.model.WorkVariant;
import org.springframework.scheduling.config.Task;

import java.lang.reflect.Array;
import java.util.List;

public interface TaskService {
    void saveTask(Tasks tasks);

    void delete(Long id);

    void modify(Long id, Status status);

    List<EmployeeTaskDto> usersByTaskId(Long id);

    List<Tasks> findTaskSendByUsername(String username);

    List<Tasks> findTaskGetByUsername(String username);

    StatisticDto findCountTaskByUsername (String username);

    List<Tasks> findTaskByUserNameWithFilter (String username, Status status, WorkVariant workVariant);
}
