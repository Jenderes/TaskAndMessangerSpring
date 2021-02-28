package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GROUPMESSAGES")
public class GroupMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupMessageId;
    private String groupMassageText;

    private String type_message;
    @Temporal(TemporalType.DATE)
    private Date sendDate;
    @ManyToOne
    @JoinColumn(name = "message_from_user_id")
    private Employee employeesmessage;
    @ManyToOne
    @JoinColumn(name = "message_for_group_id")
    private Group messagesgroup;

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Employee getEmployeesmessage() {
        return employeesmessage;
    }

    public void setEmployeesmessage(Employee employeesmessage) {
        this.employeesmessage = employeesmessage;
    }

    public String getGroupMassageText() {
        return groupMassageText;
    }

    public void setGroupMassageText(String groupMassageText) {
        this.groupMassageText = groupMassageText;
    }

    public Group getMessagesgroup() {
        return messagesgroup;
    }

    public void setMessagesgroup(Group messagesgroup) {
        this.messagesgroup = messagesgroup;
    }
}
