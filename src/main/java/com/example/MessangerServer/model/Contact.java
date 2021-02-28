package com.example.MessangerServer.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private String status;
    @ManyToOne
    @JoinColumn(name="ContactUserId")
    private Employee ContactUserId;

    @ManyToOne
    @JoinColumn(name="ReceivedId")
    private Employee ContactReceivedId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Employee getContactUserId() {
        return ContactUserId;
    }

    public void setContactUserId(Employee contactUserId) {
        ContactUserId = contactUserId;
    }

    public Employee getContactReceivedId() {
        return ContactReceivedId;
    }

    public void setContactReceivedId(Employee contactReceivedId) {
        ContactReceivedId = contactReceivedId;
    }
}
