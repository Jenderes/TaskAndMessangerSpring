package com.example.task_manager.dto;

import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Projects;

public class ProjectDto {
    private long projectId;
    private long projectManagerId;
    private String projectName;
    private String projectManagerFullName;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(long projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManagerFullName() {
        return projectManagerFullName;
    }

    public void setProjectManagerFullName(String projectManagerFullName) {
        this.projectManagerFullName = projectManagerFullName;
    }

    public static ProjectDto convertDTO (Projects projects) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.projectId = projects.getProjectId();
        projectDto.projectName = projects.getName();
        Employee projectManager = projects.getProjectManager();
        projectDto.projectManagerId = projectManager.getUserId();
        projectDto.projectManagerFullName = projectManager.getFirstName() +" "+projectManager.getLastName();
        return projectDto;
    }
}
