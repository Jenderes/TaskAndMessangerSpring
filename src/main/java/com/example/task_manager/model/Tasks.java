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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_employee")
    private Employee recipientEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_employee")
    private Employee senderEmployee;

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

    public Employee getRecipientEmployee() {
        return recipientEmployee;
    }

    public void setRecipientEmployee(Employee recipientEmployee) {
        this.recipientEmployee = recipientEmployee;
    }

    public Employee getSenderEmployee() {
        return senderEmployee;
    }

    public void setSenderEmployee(Employee senderEmployee) {
        this.senderEmployee = senderEmployee;
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

    public TaskDto convertTaskToTaskDTO () {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(this.getTaskId());
        taskDto.setTaskHead(this.getTextTask());
        taskDto.setTaskDescription(this.getTextBody());
        taskDto.setDateStart(this.getSendDate());
        taskDto.setDateEnd(this.getEndDate());
        taskDto.setWorkVariant(this.getWorkVariant());
        Employee employeeSender = this.getRecipientEmployee();
        taskDto.setTaskFullNameSender(employeeSender.getFirstName() + " " + employeeSender.getLastName());
        return taskDto;
    }

}
