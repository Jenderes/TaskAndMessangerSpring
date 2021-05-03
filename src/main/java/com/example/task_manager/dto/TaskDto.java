package com.example.task_manager.dto;

import com.example.task_manager.model.WorkVariant;

import java.util.Date;

public class TaskDto {

    private Long id;
    private Date dateStart;
    private Date dateEnd;
    private String taskHead;
    private WorkVariant workVariant;
    private String taskDescription;
    private String taskFullNameSender;
    private Long[] taskGetEmployeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTaskHead() {
        return taskHead;
    }

    public void setTaskHead(String taskHead) {
        this.taskHead = taskHead;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Long[] getTaskGetEmployeeId() {
        return taskGetEmployeeId;
    }

    public void setTaskGetEmployeeId(Long[] tasGetEmployeeId) {
        this.taskGetEmployeeId = tasGetEmployeeId;
    }

    public WorkVariant getWorkVariant() {
        return workVariant;
    }

    public void setWorkVariant(WorkVariant workVariant) {
        this.workVariant = workVariant;
    }

    public String getTaskFullNameSender() {
        return taskFullNameSender;
    }

    public void setTaskFullNameSender(String taskFullNameSender) {
        this.taskFullNameSender = taskFullNameSender;
    }
}
