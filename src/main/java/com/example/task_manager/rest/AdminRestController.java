package com.example.task_manager.rest;

import com.example.task_manager.dto.DepartmentDto;
import com.example.task_manager.dto.MessageDto;
import com.example.task_manager.dto.ProjectDto;
import com.example.task_manager.dto.TaskDto;
import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;
import com.example.task_manager.security.jwt.JwtTokenProvider;
import com.example.task_manager.service.AdminService;
import com.example.task_manager.service.ManagerService;
import com.example.task_manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/admin")
@Slf4j
public class AdminRestController {

    private final AdminService adminService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ManagerService managerService;

    public AdminRestController(AdminService adminService, UserService userService, JwtTokenProvider jwtTokenProvider, ManagerService managerService) {
        this.adminService = adminService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.managerService = managerService;
    }

    @GetMapping("get_project")
    public List<ProjectDto> testingResponse(HttpServletRequest request) {
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee sender = userService.findByUsername(username);
        log.info("get username from token - username: {}",username);
        List<String> roles = jwtTokenProvider.getRolesName(sender.getRoles());
        List<ProjectDto> projectsDto = new ArrayList<>();
        if (roles.contains("ROLE_ADMIN")){
            projectsDto = adminService.getAllProjects()
                    .stream()
                    .map(ProjectDto::convertDTO)
                    .collect(Collectors.toList());
        }
        return projectsDto;
    }

    @PostMapping("create_department")
    public ResponseEntity<?> creatDepartment(HttpServletRequest request,
                                             @RequestBody DepartmentDto departmentDto) {

        return ResponseEntity.ok(new MessageDto("department_created"));
    }

    @PostMapping("create_project")
    public ResponseEntity<?> createProject() {

        return ResponseEntity.ok(new MessageDto("project_created"));
    }

    public void addDepartmentInProject() {

    }

    public void addEmployeeInDepartment() {

    }

    public void CloseProject() {

    }

    public void closeDepartment() {

    }

    public void deleteEmployee() {

    }
}