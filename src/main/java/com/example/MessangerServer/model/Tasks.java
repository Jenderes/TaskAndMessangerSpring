package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TASKS")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;
    private String textTask;
    private String textBody;
    @Temporal(TemporalType.TIME)
    private Date sendDate;
    @Temporal(TemporalType.TIME)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_task_id")
    private List<Employee> taskForEmployees;

    @ManyToOne()
    @JoinColumn(name = "get_task_id")
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

    public Status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Employee getTaskFromEmployees() {
        return taskFromEmployees;
    }

    public void setTaskFromEmployees(Employee taskFromEmployees) {
        this.taskFromEmployees = taskFromEmployees;
    }

    public List<Employee> getTaskForEmployees() {
        return taskForEmployees;
    }

    public void setTaskForEmployees(List<Employee> taskForEmployees) {
        this.taskForEmployees = taskForEmployees;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
}
