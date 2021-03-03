package com.example.MessangerServer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "confirmation_token")
    private String ConfirmationToken;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Employee employee;
}
