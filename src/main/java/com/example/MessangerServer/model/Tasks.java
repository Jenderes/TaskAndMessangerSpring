package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String textTask;
    @Temporal(TemporalType.TIME)
    private Date sendDate;
    @Temporal(TemporalType.TIME)
    private Date endDate;
    private String status;
    @ManyToOne
    @JoinColumn(name = "sendTaskId")
    private Employee taskForEmployees;

    @ManyToOne
    @JoinColumn(name = "sendTaskRecipient")
    private Employee taskFromEmployees;

    public String getTextTask() {
        return textTask;
    }

    public void setTextTask(String textTask) {
        this.textTask = textTask;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getTaskForEmployees() {
        return taskForEmployees;
    }

    public void setTaskForEmployees(Employee taskForEmployees) {
        this.taskForEmployees = taskForEmployees;
    }

    public Employee getTaskFromEmployees() {
        return taskFromEmployees;
    }

    public void setTaskFromEmployees(Employee taskFromEmployees) {
        this.taskFromEmployees = taskFromEmployees;
    }
}
