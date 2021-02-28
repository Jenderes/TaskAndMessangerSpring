package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REQUESTS")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.TIME)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name="requestId")
    private Employee requestForEmployees;

    @ManyToOne
    @JoinColumn(name="requestReceivedId")
    private Employee requestFromEmployees;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Employee getRequestForEmployees() {
        return requestForEmployees;
    }

    public void setRequestForEmployees(Employee requestForEmployees) {
        this.requestForEmployees = requestForEmployees;
    }

    public Employee getRequestFromEmployees() {
        return requestFromEmployees;
    }

    public void setRequestFromEmployees(Employee requestFromEmployees) {
        this.requestFromEmployees = requestFromEmployees;
    }
}
