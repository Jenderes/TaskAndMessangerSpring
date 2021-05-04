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
}
