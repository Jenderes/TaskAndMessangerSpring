package com.example.task_manager.rest;

import com.example.task_manager.dto.DepartmentDto;
import com.example.task_manager.dto.MessageDto;
import com.example.task_manager.dto.ProjectDto;
import com.example.task_manager.dto.ResendDto;
import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Role;
import com.example.task_manager.model.Tasks;
import com.example.task_manager.repository.RoleRepository;
import com.example.task_manager.security.jwt.JwtTokenProvider;
import com.example.task_manager.service.ManagerService;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/manager/")
@Slf4j
public class ManagerRestController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TaskService taskService;
    private final ManagerService managerService;
    private final RoleRepository roleRepository;

    public ManagerRestController(JwtTokenProvider jwtTokenProvider, UserService userService, TaskService taskService, ManagerService managerService, RoleRepository roleRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.taskService = taskService;
        this.managerService = managerService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("get_departments")
    public List<DepartmentDto> testingResponse(HttpServletRequest request) {
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee sender = userService.findByUsername(username);
        log.info("get username from token - username: {}",username);
        List<String> roles = jwtTokenProvider.getRolesName(sender.getRoles());
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        if (roles.contains("ROLE_PM")){
            List<Department> departments = managerService.getDepartmentsByEmployeeProjectManager(sender);
            departmentsDto = departments.stream().map(DepartmentDto::convertToDTO).collect(Collectors.toList());
        }
        return departmentsDto;
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
            if (resendDto.getDepartmentId() == 0 || resendDto.getDepartmentId() == taskResend.getTaskDepartment().getDepartmentId()){
                if (resendDto.getEmployeeId().length == 0) return ResponseEntity.ok(new MessageDto("Empty department id and employee id"));
                taskResend.setTaskForEmployees(Arrays.stream(resendDto.getEmployeeId()).mapToObj(userService::findById).collect(Collectors.toList()));
                taskService.saveTask(taskResend);
            } else {
                    taskResend.setTaskDepartment(managerService.getDepartmentById(resendDto.getDepartmentId()));
                    taskResend.setTaskForEmployees(null);
                    taskService.saveTask(taskResend);
            }
        return ResponseEntity.ok(new MessageDto("task resend"));
    }
}
