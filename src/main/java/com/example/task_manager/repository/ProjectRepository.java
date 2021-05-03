package com.example.task_manager.repository;

import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects, Long> {

    Projects findProjectsByProjectManager(Employee projectManager);
    Projects findProjectByProjectId(long projectId);
    List<Projects> findProjectsByProjectDepartmentIsNull();
}
