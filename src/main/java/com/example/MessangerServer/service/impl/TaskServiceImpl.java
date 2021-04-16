package com.example.MessangerServer.service.impl;

import com.example.MessangerServer.dto.EmployeeTaskDto;
import com.example.MessangerServer.dto.StatisticDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.model.Tasks;
import com.example.MessangerServer.model.WorkVariant;
import com.example.MessangerServer.repository.EmployeeRepository;
import com.example.MessangerServer.repository.TasksRepository;
import com.example.MessangerServer.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl  implements TaskService {

    private final TasksRepository tasksRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskServiceImpl(TasksRepository tasksRepository, EmployeeRepository employeeRepository) {
        this.tasksRepository = tasksRepository;
        this.employeeRepository = employeeRepository;
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

    @Override
    public List<EmployeeTaskDto> usersByTaskId(Long id) {
        Tasks task = tasksRepository.findByTaskId(id);
        List<Employee> employeeList = task.getTaskForEmployees();
        List<EmployeeTaskDto> employeeDTO = employeeList.stream().map(employee ->
            new EmployeeTaskDto(
                    employee.getUserId(),
                    employee.getFirstName(),
                    employee.getFirstName()
            )).collect(Collectors.toList());
        log.info("usersByTaskId list: {} to task id: {}", employeeDTO, id);
        return employeeDTO;
    }

    @Override
    public List<Tasks> findTaskSendByUsername(String username) {
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskList = empl.getTaskFrom();
        log.info("IN findTaskSendByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }

    @Override
    public List<Tasks> findTaskGetByUsername(String username) {
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskList = empl.getTaskFor();
        log.info("IN findTaskGetByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }
    //TODO: change on forEach
    @Override
    public StatisticDto findCountTaskByUsername(String username) {
        StatisticDto statisticDto= new StatisticDto();
        Employee empl = employeeRepository.findByUsername(username);
        List<Tasks> taskListFrom = empl.getTaskFrom();
        statisticDto.setCountSendTask((long)taskListFrom.size());
        List<Tasks> taskListFor = empl.getTaskFor();
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

    @Override
    public List<Tasks> findTaskByUserNameWithFilter(String username, Status status, WorkVariant workVariant) {
        Employee empl = employeeRepository.findByUsername(username);
        return tasksRepository.findDistinctByTaskFromEmployeesAndTaskStatusAndWorkVariant(empl, status, workVariant);
    }

}
