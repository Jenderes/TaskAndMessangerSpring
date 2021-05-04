package com.example.task_manager.service;

import com.example.task_manager.dto.DepartmentDto;
import com.example.task_manager.dto.ProjectDto;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public void createProject(ProjectDto projectDto) {

        projectRepository.save(new Projects(projectDto.getProjectName()));
    }

    public void createDepartment(DepartmentDto departmentDto) {

        departmentRepository.save(new Department(departmentDto.getDepartmentName()));
    }

    public void setDepartmentToEmployeeByEmployeeId(long departmentId, List<Long> employeeId) {
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        List<Employee> newDepartments = employeeId.stream()
                .map(employeeRepository::findByUserId)
                .collect(Collectors.toList());
        List<Employee> employeeDepartment = department.getEmployeeDepartment();
        if(employeeDepartment.size() != 0){
            Set<Employee> employeeSet = new HashSet<>(employeeDepartment);
            employeeSet.addAll(newDepartments);
            department.setEmployeeDepartment(new ArrayList<>(employeeSet));
        } else {
            department.setEmployeeDepartment(newDepartments);
        }
        departmentRepository.save(department);
    }

    public void setProjectToDepartmentByDepartmentID(long projectId, List<Long> departmentsId) {
        Projects projects = projectRepository.findProjectByProjectId(projectId);
        List<Department> departments = departmentsId.stream()
                .map(departmentRepository::findDepartmentByDepartmentId)
                .collect(Collectors.toList());
        List<Department> departmentProject = projects.getProjectDepartment();
        if (departmentProject.size() != 0) {
            Set<Department> departmentSet = new HashSet<>(departmentProject);
            departmentSet.addAll(departments);
            projects.setProjectDepartment(new ArrayList<>(departmentSet));
        } else {
            projects.setProjectDepartment(departments);
        }
        projectRepository.save(projects);
    }

    public void setManagerDepartmentById(long departmentId, long employeeId) {
        Department department = departmentRepository.findDepartmentByDepartmentId(departmentId);
        Employee employee = employeeRepository.findByUserId(employeeId);
        department.setDepartmentManager(employee);
        departmentRepository.save(department);
    }

    public void setManagerProjectById(long projectId, long employeeId) {
        Projects projects = projectRepository.findProjectByProjectId(projectId);
        Employee employee = employeeRepository.findByUserId(employeeId);
        projects.setProjectManager(employee);
        projectRepository.save(projects);
    }

    public List<Employee> getDepartmentManagersWithoutDepartment() {
        Role roleDm = roleRepository.findByName("ROLE_DM");
        List<Employee> departmentManagerList = roleDm.getEmployees();
        return departmentManagerList.stream()
                .filter(departmentManager -> departmentManager.getManagerDepartment() == null)
                .collect(Collectors.toList());
    }

    public List<Employee> getProjectManagersWithoutProject() {
        Role rolePm = roleRepository.findByName("ROLE_PM");
        List<Employee> projectManagerList = rolePm.getEmployees();
        return projectManagerList.stream()
                .filter(departmentManager -> departmentManager.getManagerProjects() == null)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWithoutDepartment() {
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

    public void deleteProject(long projectId) {
        projectRepository.delete(projectRepository.findProjectByProjectId(projectId));
    }

    public void deleteDepartment(long departmentId) {
        departmentRepository.delete(departmentRepository.findDepartmentByDepartmentId(departmentId));
    }

}
