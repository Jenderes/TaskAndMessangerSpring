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
    @NotNull
    private String textMessage;
    @Temporal (TemporalType.DATE)
    private Date  sendDate;
    private String typeMessage;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="message_for_user_id", referencedColumnName="userId"),
            @JoinColumn(name="message_from_user_id", referencedColumnName="userId")
    })
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
}
