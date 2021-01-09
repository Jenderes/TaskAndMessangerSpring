package com.example.MessangerServer.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MESSAGES")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;
    private String textMessage;
    @Temporal (TemporalType.DATE)
    private Date  sendDate;
    private String typeMessage;
    @ManyToOne
    @JoinColumn(name="messageForEmployee")
    private Employees messageForEmployees;

    @ManyToOne
    @JoinColumn(name="messageFromEmployee")
    private Employees messageFromEmployees;

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

    public Employees getMessageForEmployees() {
        return messageForEmployees;
    }

    public void setMessageForEmployees(Employees messageForEmployees) {
        this.messageForEmployees = messageForEmployees;
    }

    public Employees getMessageFromEmployees() {
        return messageFromEmployees;
    }

    public void setMessageFromEmployees(Employees messageFromEmployees) {
        this.messageFromEmployees = messageFromEmployees;
    }
}
