package com.example.task_manager.repository;

import com.example.task_manager.model.Department;
import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


    Department findDepartmentByDepartmentId(long departmentId);

    Department findDepartmentByDepartmentManager(Employee departmentManager);

    List<Department> findDepartmentsByDepartmentManagerIsNull();

    List<Department> findDepartmentsByProjects(Projects projects);

    List<Department> findDepartmentsByProjectsIsNull();
}
