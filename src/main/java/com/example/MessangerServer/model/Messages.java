package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String textMessage;
    @Temporal(TemporalType.DATE)
    private Date sendDate;

    private String typeMessage;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Employee> employeesSetMessage;

    @ManyToOne
    @JoinColumn(name = "messageFromEmployee")
    private Employee messageFromEmployees;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Employee getMessageFromEmployees() {
        return messageFromEmployees;
    }

    public void setMessageFromEmployees(Employee messageFromEmployees) {
        this.messageFromEmployees = messageFromEmployees;
    }

    public Set<Employee> getEmployeesSetMessage() {
        return employeesSetMessage;
    }

    public void setEmployeesSetMessage(Set<Employee> employeesSetMessage) {
        this.employeesSetMessage = employeesSetMessage;
    }
}
