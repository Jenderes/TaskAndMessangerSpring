package com.example.MessangerServer.model;

import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;
    private String status;
    @ManyToOne
    @JoinColumn(name="sendForId")
    private Employees contactForEmployees;

    @ManyToOne
    @JoinColumn(name="sendFromId")
    private Employees contactFromEmployees;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employees getContactForEmployees() {
        return contactForEmployees;
    }

    public void setContactForEmployees(Employees contactForEmployees) {
        this.contactForEmployees = contactForEmployees;
    }

    public Employees getContactFromEmployees() {
        return contactFromEmployees;
    }

    public void setContactFromEmployees(Employees contactFromEmployees) {
        this.contactFromEmployees = contactFromEmployees;
    }
}
