package com.example.MessangerServer.dto;

import org.hibernate.exception.DataException;

import java.util.Date;
import java.util.List;

public class TaskDto {

    private Long id;
    private Date dateStart;
    private Date dateEnd;
    private String taskHead;
    private String taskDescription;
    private Long[] tasGetEmployeeId;

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

    public Long[] getTasGetEmployeeId() {
        return tasGetEmployeeId;
    }

    public void setTasGetEmployeeId(Long[] tasGetEmployeeId) {
        this.tasGetEmployeeId = tasGetEmployeeId;
    }
}
