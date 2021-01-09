package com.example.MessangerServer.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TASKS")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String textTask;
    @Temporal (TemporalType.DATE)
    private Date sendDate;
    @Temporal (TemporalType.DATE)
    private Date endDate;
    private String status;
    @ManyToOne
    @JoinColumn(name="sendTaskFor")
    private Employees taskForEmployees;

    @ManyToOne
    @JoinColumn(name="sendTaskFrom")
    private Employees taskFromEmployees;

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

    public Employees getTaskForEmployees() {
        return taskForEmployees;
    }

    public void setTaskForEmployees(Employees taskForEmployees) {
        this.taskForEmployees = taskForEmployees;
    }

    public Employees getTaskFromEmployees() {
        return taskFromEmployees;
    }

    public void setTaskFromEmployees(Employees taskFromEmployees) {
        this.taskFromEmployees = taskFromEmployees;
    }
}
