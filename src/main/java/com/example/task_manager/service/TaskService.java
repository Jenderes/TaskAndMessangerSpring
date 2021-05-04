package com.example.task_manager.service;

import com.example.task_manager.dto.EmployeeTaskDto;
import com.example.task_manager.dto.StatisticDto;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Status;
import com.example.task_manager.model.Tasks;
import com.example.task_manager.model.WorkVariant;
import com.example.task_manager.repository.EmployeeRepository;
import com.example.task_manager.repository.TasksRepository;
import com.example.task_manager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {

    private final TasksRepository tasksRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskService(TasksRepository tasksRepository, EmployeeRepository employeeRepository) {
        this.tasksRepository = tasksRepository;
        this.employeeRepository = employeeRepository;
    }


    public void saveTask(Tasks tasks) {
        tasksRepository.save(tasks);
        log.info("saveTask: task save");
    }

    public void delete(Long id) {
        Tasks task = tasksRepository.findByTaskId(id);
        tasksRepository.delete(task);
        log.info("delete task by id: {}",task);
    }

    public void modify(Long id, Status status) {
        Tasks task = tasksRepository.findByTaskId(id);
        task.setTaskStatus(status);
        saveTask(task);
        log.info("Change status task:{} toStatus: {}", task, status);
    }

    public EmployeeTaskDto recipientEmployeeByTaskId(Long id) {
        Tasks task = tasksRepository.findByTaskId(id);
        Employee recipientEmployee = task.getRecipientEmployee();
        EmployeeTaskDto employeeDTO = new EmployeeTaskDto(
                recipientEmployee.getUserId(),
                recipientEmployee.getFirstName(),
                recipientEmployee.getFirstName()
            );
        log.info("usersByTaskId list: {} to task id: {}", employeeDTO, id);
        return employeeDTO;
    }

    public List<Tasks> findSentTasksByUsername(String username) {
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskList = empl.getSentTasks();
        log.info("IN findTaskSendByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }

    public List<Tasks> findReceivedTasksByUsername(String username) {
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskList = empl.getReceivedTasks();
        log.info("IN findTaskGetByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }
    //TODO: change on forEach
    public StatisticDto findCountTaskByUsername(String username) {
        StatisticDto statisticDto= new StatisticDto();
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskListFrom = empl.getReceivedTasks();
        statisticDto.setCountSendTask((long)taskListFrom.size());
        List<Tasks> taskListFor = empl.getSentTasks();
        statisticDto.setCountGetTask((long) taskListFor.size());
        List<Tasks> completeTask = taskListFor.stream().filter(el ->
                el.getTaskStatus() == Status.COMPLETE).collect(Collectors.toList());
        statisticDto.setCountCompleteTask((long)completeTask.size());
        statisticDto.setCountDevelopTask(
                completeTask.stream()
                        .filter(task -> task.getWorkVariant() == WorkVariant.DEVELOP)
                        .count()
        );
        statisticDto.setCountDesignTask(
                completeTask.stream()
                        .filter(task -> task.getWorkVariant() == WorkVariant.DESIGN)
                        .count()
        );
        statisticDto.setCountBusinessTask(
                completeTask.stream()
                        .filter(task -> task.getWorkVariant() == WorkVariant.BUSINESS)
                        .count()
        );
        statisticDto.setCountAnalyseTask(
                completeTask.stream()
                        .filter(task -> task.getWorkVariant() == WorkVariant.TESTING)
                        .count()
        );
        return statisticDto;
    }

    public List<Tasks> findTaskByUserNameWithFilter(String username, Status status, WorkVariant workVariant) {
        Employee empl = employeeRepository.findByUsername(username);
        return tasksRepository.findAllBySenderEmployeeAndTaskStatusAndWorkVariant(empl, status, workVariant);
    }

    public Tasks findTaskById(long taskId) {
        log.info("IN findTaskById - Task By id: {}",taskId);
        return tasksRepository.findByTaskId(taskId);
    }

}
