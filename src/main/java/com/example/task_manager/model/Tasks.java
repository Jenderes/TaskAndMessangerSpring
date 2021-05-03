package com.example.task_manager.model;

import com.example.task_manager.dto.TaskDto;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department taskDepartment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Projects taskProjects;

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

    public Department getTaskDepartment() {
        return taskDepartment;
    }

    public void setTaskDepartment(Department taskDepartment) {
        this.taskDepartment = taskDepartment;
    }

    public Projects getTaskProjects() {
        return taskProjects;
    }

    public void setTaskProjects(Projects taskProjects) {
        this.taskProjects = taskProjects;
    }

    public TaskDto convertTaskToTaskDTO () {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(this.getTaskId());
        taskDto.setTaskHead(this.getTextTask());
        taskDto.setTaskDescription(this.getTextBody());
        taskDto.setDateStart(this.getSendDate());
        taskDto.setDateEnd(this.getEndDate());
        taskDto.setWorkVariant(this.getWorkVariant());
        Employee employeeSender = this.getTaskFromEmployees();
        taskDto.setTaskFullNameSender(employeeSender.getFirstName() + " " + employeeSender.getLastName());
        return taskDto;
    }

}
