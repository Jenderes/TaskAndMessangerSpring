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
    @Temporal(TemporalType.DATE)
    private Date sendDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private Status taskStatus;
    @Enumerated(EnumType.STRING)
    private WorkVariant workVariant;

    //TODO: change column name reverse
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_task_get",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Employee> taskForEmployees;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public WorkVariant getWorkVariant() {
        return workVariant;
    }

    public void setWorkVariant(WorkVariant workVariant) {
        this.workVariant = workVariant;
    }
}
