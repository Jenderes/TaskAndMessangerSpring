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
    @JoinColumns({
            @JoinColumn(name="contact_for_user_id", referencedColumnName="userId"),
            @JoinColumn(name="contact_from_user_id", referencedColumnName="userId")
    })
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
