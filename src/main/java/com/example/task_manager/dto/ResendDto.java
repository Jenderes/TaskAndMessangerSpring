package com.example.task_manager.dto;

import java.util.List;

public class ResendDto {
    private long taskId;
    private long employeeId;
    private long departmentId;

    public ResendDto() {
    }

    public ResendDto(long taskId, long employeeId, long departmentId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
        this.departmentId = departmentId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long  employeeId) {
        this.employeeId = employeeId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}
