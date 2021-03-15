package com.example.MessangerServer.rest;


import com.example.MessangerServer.dto.StatisticDto;
import com.example.MessangerServer.dto.TaskDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.model.Tasks;
import com.example.MessangerServer.security.jwt.JwtTokenProvider;
import com.example.MessangerServer.service.TaskService;
import com.example.MessangerServer.service.UserService;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/task/")
public class TaskRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TaskService taskService;

    public TaskRestController(JwtTokenProvider jwtTokenProvider, UserService userService, TaskService taskService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("send")
    public List<TaskDto> getAllTaskSendEmployee(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        return taskService.findTaskSendByUsername(username).stream().map( task -> {
            final TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getTaskId());
            taskDto.setTaskHead(task.getTextTask());
            taskDto.setTaskDescription(task.getTextBody());
            taskDto.setDateStart(task.getSendDate());
            taskDto.setDateEnd(task.getEndDate());
            return taskDto;
        }).collect(Collectors.toList());

    }
    @GetMapping("get")
    public List<TaskDto> getAllTaskGetEmployee(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        return taskService.findTaskGetByUsername(username).stream().map( task -> {
            final TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getTaskId());
            taskDto.setTaskHead(task.getTextTask());
            taskDto.setTaskDescription(task.getTextBody());
            taskDto.setDateStart(task.getSendDate());
            taskDto.setDateEnd(task.getEndDate());
            return taskDto;
        }).collect(Collectors.toList());
    }
    @PostMapping("save")
    public ResponseEntity<String> saveTask (HttpServletRequest request,
                                            @RequestBody TaskDto taskDto){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee sentEmployee = userService.findByUsername(username);
        List<Employee> listEmployee = Arrays.stream(taskDto.getTasGetEmployeeId()).map(userService::findById).collect(Collectors.toList());
        Tasks tasks = new Tasks();
        tasks.setTextTask(taskDto.getTaskHead());
        tasks.setTextBody(taskDto.getTaskDescription());
        tasks.setTaskStatus(Status.ACTIVE);
        tasks.setTaskForEmployees(listEmployee);
        tasks.setTaskFromEmployees(sentEmployee);
        tasks.setSendDate(taskDto.getDateStart());
        tasks.setEndDate(taskDto.getDateEnd());
        tasks.setWorkVariant(taskDto.getWorkVariant());
        taskService.saveTask(tasks);
        return ResponseEntity.ok("Task save");
    }
    @PostMapping("change")
    public ResponseEntity<String> changeStatusTask (@RequestParam(name = "id") Long id, @RequestParam(name = "status") Status status){
        taskService.modify(id, status);
        return ResponseEntity.ok("Task change Status: "+ status);
    }
}
