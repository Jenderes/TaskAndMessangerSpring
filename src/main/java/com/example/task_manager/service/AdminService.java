package com.example.task_manager.service;

import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;
import com.example.task_manager.model.Role;
import com.example.task_manager.repository.DepartmentRepository;
import com.example.task_manager.repository.EmployeeRepository;
import com.example.task_manager.repository.ProjectRepository;
import com.example.task_manager.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminService {
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public AdminService(ProjectRepository projectRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    public void createProject(String name) {
        Projects createProjects = new Projects(name);
        projectRepository.save(createProjects);
    }

    public void createDepartment(String name) {
        Department createDepartment = new Department();
        departmentRepository.save(createDepartment);
    }

    public void setDepartmentToEmployeeByEmployeeId(long departmentId, long employeeId) {
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        Employee employee = employeeRepository.findByUserId(employeeId);
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    public void setProjectToDepartmentByDepartmentID(long projectId, long departmentId) {
        Projects projects = projectRepository.findProjectByProjectId(projectId);
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        department.setProjects(projects);
        departmentRepository.save(department);
    }

    public void setManagerDepartmentById(long departmentId, long employeeId) {
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        Employee employee = employeeRepository.findByUserId(employeeId);
    }

    public void setManagerProjectById(long projectId, long employeeId) {
        Projects projects = projectRepository.findProjectByProjectId(projectId);
        Employee employee = employeeRepository.findByUserId(employeeId);
    }

    public List<Employee> getDepartmentManagersWithoutDepartment() {
        Role roleDM = roleRepository.findByName("ROLE_DM");
        List<Role> roles = new ArrayList<>();
        roles.add(roleDM);
        return employeeRepository.findEmployeesByDepartmentIsNull();
    }

    public List<Employee> getEmployeesWithoutDepartment() {
        Role roleDM = roleRepository.findByName("ROLE_DM");
        List<Role> roles = new ArrayList<>();
        roles.add(roleDM);
        return employeeRepository.findEmployeesByDepartmentIsNull();
    }

    public List<Department> getDepartmentWithoutProject() {
        return departmentRepository.findDepartmentsByProjectsIsNull();
    }

    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Projects> getProjectsWithoutDepartment() {
        return projectRepository.findProjectsByProjectDepartmentIsNull();
    }
}
