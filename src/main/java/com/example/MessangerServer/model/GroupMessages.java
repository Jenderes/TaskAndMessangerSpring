package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GROUPMESSAGES")
public class GroupMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_message_id;
    private String gr_msg_text;

    private String type_message;
    @Temporal (TemporalType.DATE)
    private Date sendDate;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="message_for_user_id", referencedColumnName="userId"),
            @JoinColumn(name="message_from_user_id", referencedColumnName="groupId")
    })
    public String getGr_msg_text() {
        return gr_msg_text;
    }

    public void setGr_msg_text(String gr_msg_text) {
        this.gr_msg_text = gr_msg_text;
    }

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
}
