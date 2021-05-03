package com.example.task_manager.dto;

import java.util.List;

public class ProjectCreateDto {
    String projectName;
    List<Integer> departmentLIst;

    public ProjectCreateDto() {
    }

    public ProjectCreateDto(String projectName, List<Integer> departmentLIst) {
        this.projectName = projectName;
        this.departmentLIst = departmentLIst;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Integer> getDepartmentLIst() {
        return departmentLIst;
    }

    public void setDepartmentLIst(List<Integer> departmentLIst) {
        this.departmentLIst = departmentLIst;
    }
}
