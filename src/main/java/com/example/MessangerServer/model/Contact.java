package com.example.MessangerServer.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT")
public class Contact {
    public Contact() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name="contact_employee_id")
    private Employee ContactUserId;

    @ManyToOne
    @JoinColumn(name="received_employee_id")
    private Employee ContactReceivedId;

    public Contact(Status status, Employee contactUserId, Employee contactReceivedId) {
        this.status = status;
        ContactUserId = contactUserId;
        ContactReceivedId = contactReceivedId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
