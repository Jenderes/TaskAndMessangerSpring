package com.example.MessangerServer.service.impl;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.model.Tasks;
import com.example.MessangerServer.repository.TasksRepository;
import com.example.MessangerServer.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl  implements TaskService {

    private final TasksRepository tasksRepository;

    @Autowired
    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }


    @Override
    public void saveTask(Tasks tasks) {
        tasksRepository.save(tasks);
        log.info("saveTask: task save");
    }

    @Override
    public void delete(Long id) {
        Tasks task = tasksRepository.findByTaskId(id);
        tasksRepository.delete(task);
        log.info("delete task by id: {}",task);
    }

    @Override
    public void modify(Long id, Status status) {
        Tasks task = tasksRepository.findByTaskId(id);
        task.setTaskStatus(status);
        saveTask(task);
        log.info("Change status task:{} toStatus: {}", task, status);
    }
}
