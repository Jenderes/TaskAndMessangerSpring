package com.example.task_manager.rest;


import com.example.task_manager.dto.MessageDto;
import com.example.task_manager.dto.ResendDto;
import com.example.task_manager.dto.TaskDto;
import com.example.task_manager.model.*;
import com.example.task_manager.security.jwt.JwtTokenProvider;
import com.example.task_manager.service.ManagerService;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/task/")
@Slf4j
public class TaskRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TaskService taskService;
    private ManagerService managerService;

    public TaskRestController(JwtTokenProvider jwtTokenProvider, UserService userService,
                              TaskService taskService, ManagerService managerService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.taskService = taskService;
        this.managerService = managerService;
    }

    @GetMapping("send")
    public List<TaskDto> getAllTaskSendEmployee(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        return taskService.findSentTasksByUsername(username).stream().map(Tasks::convertTaskToTaskDTO).collect(Collectors.toList());

    }
    @GetMapping("get")
    public List<TaskDto> getAllTaskGetEmployee(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        return taskService.findReceivedTasksByUsername(username)
                .stream()
                .map(Tasks::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }
    @PostMapping("save")
    public ResponseEntity<?> saveTask (HttpServletRequest request,
                                            @RequestBody TaskDto taskDto){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee senderEmployee = userService.findByUsername(username);
        Employee recipientEmployee = userService.findById(taskDto.getEmployeeRecipientId());
        Tasks tasks = new Tasks();
        tasks.setTextTask(taskDto.getTaskHead());
        tasks.setTextBody(taskDto.getTaskDescription());
        tasks.setTaskStatus(Status.ACTIVE);
        tasks.setRecipientEmployee(recipientEmployee);
        tasks.setSenderEmployee(senderEmployee);
        tasks.setSendDate(taskDto.getDateStart());
        tasks.setEndDate(taskDto.getDateEnd());
        tasks.setWorkVariant(taskDto.getWorkVariant());
        taskService.saveTask(tasks);
        return ResponseEntity.ok(new MessageDto("Пользователь зарегистрирован"));
    }
    @PostMapping("change")
    public ResponseEntity<String> changeStatusTask (
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "status") Status status) {

        taskService.modify(id, status);
        return ResponseEntity.ok("Task change Status: "+ status);
    }
    //TODO доделать получения данных
    @GetMapping("filter")
    public List<TaskDto> getFilterTask (HttpServletRequest request,
                                        @RequestParam(name = "status") Status status,
                                        @RequestParam(name = "work") WorkVariant work) {
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        return taskService.findTaskByUserNameWithFilter(username, status, work)
                .stream()
                .map(Tasks::convertTaskToTaskDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("resend")
    public ResponseEntity<?> resendTask(HttpServletRequest request,
                                        @RequestBody ResendDto resendDto) {
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee sender = userService.findByUsername(username);
        log.info("get username from token - username: {}",username);
        List<String> roles = jwtTokenProvider.getRolesName(sender.getRoles());
        log.info("get list roles from employee - username: {}",roles.toString());
        if (resendDto.getTaskId() == 0) return ResponseEntity.ok(new MessageDto("Empty task id"));
        Tasks taskResend = taskService.findTaskById(resendDto.getTaskId());
        if (resendDto.getDepartmentId() > 0){
            taskResend.setRecipientEmployee(managerService.getDepartmentById(
                    resendDto.getDepartmentId()).getDepartmentManager()
            );
        }
        if (resendDto.getEmployeeId() > 0){
            taskResend.setRecipientEmployee(userService.findById(resendDto.getEmployeeId()));
        }
        taskService.saveTask(taskResend);
        return ResponseEntity.ok(new MessageDto("task resend"));
    }
}
