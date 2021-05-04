package com.example.task_manager.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String password;

    private String email;

    private String firstName;
    private String lastName;
    private String middleName;
    private String model;
    private String function;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipientEmployee")
    private List<Tasks> receivedTasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "senderEmployee")
    private List<Tasks> sentTasks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ContactUserId")
    private List<Contact> contactFor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ContactReceivedId")
    private List<Contact> contactFrom;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToOne(mappedBy = "departmentManager")
    private Department managerDepartment;

    @OneToOne(mappedBy = "projectManager")
    private Projects managerProjects;

    public Employee () {}

    public Employee(String username, String email, String password, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Tasks> getReceivedTasks() {
        return receivedTasks;
    }

    public void setReceivedTasks(List<Tasks> receivedTasks) {
        this.receivedTasks = receivedTasks;
    }

    public List<Tasks> getSentTasks() {
        return sentTasks;
    }

    public void setSentTasks(List<Tasks> sentTasks) {
        this.sentTasks = sentTasks;
    }

    public List<Contact> getContactFor() {
        return contactFor;
    }

    public void setContactFor(List<Contact> contactFor) {
        this.contactFor = contactFor;
    }

    public List<Contact> getContactFrom() {
        return contactFrom;
    }

    public void setContactFrom(List<Contact> contactFrom) {
        this.contactFrom = contactFrom;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getManagerDepartment() {
        return managerDepartment;
    }

    public void setManagerDepartment(Department managerDepartment) {
        this.managerDepartment = managerDepartment;
    }

    public Projects getManagerProjects() {
        return managerProjects;
    }

    public void setManagerProjects(Projects managerProjects) {
        this.managerProjects = managerProjects;
    }
}
