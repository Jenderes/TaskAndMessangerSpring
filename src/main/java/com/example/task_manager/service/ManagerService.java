package com.example.task_manager.service;

import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;
import com.example.task_manager.model.Status;
import com.example.task_manager.repository.DepartmentRepository;
import com.example.task_manager.repository.EmployeeRepository;
import com.example.task_manager.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Добавить проверку на null и exception
@Service
@Slf4j
public class ManagerService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public ManagerService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<Department> getDepartmentsByProjectId(long projectId) {
        Projects projects = projectRepository.findProjectByProjectId(projectId);
        return departmentRepository.findDepartmentsByProjects(projects);
    }

    public Projects getProjectByEmployeeId(long employeeId) {
        Employee employee = employeeRepository.findByUserId(employeeId);
        return projectRepository.findProjectsByProjectManager(employee);
    }

    public Projects getProjectByDepartmentId(long departmentId) {
        return departmentRepository.findDepartmentByDepartmentId(departmentId).getProjects();
    }

    public Department getDepartmentByEmployeeId(long employeeId) {
        Employee employee = employeeRepository.findByUserId(employeeId);
        return departmentRepository.findDepartmentByDepartmentManager(employee);
    }

    public void changeStatusEmployee(long employeeId, Status status) {
        Employee employee = employeeRepository.findByUserId(employeeId);
        employee.setStatus(status);
        employeeRepository.save(employee);
    }

    public Department getDepartmentById(long departmentId) {
        log.info("get department by id: {}",departmentId);
        return departmentRepository.findDepartmentByDepartmentId(departmentId);
    }

    public List<Department> getDepartmentsByEmployeeProjectManager(Employee employee) {
        Projects projects = projectRepository.findProjectsByProjectManager(employee);
        return departmentRepository.findDepartmentsByProjects(projects);
    }
}
