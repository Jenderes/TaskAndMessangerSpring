package com.example.MessangerServer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    private String middleName;
    private String model;
    private String function;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Temporal(TemporalType.DATE)
    private Date leaveDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskForEmployees")
    private List<Tasks> taskFor;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskFromEmployees")
    private List<Tasks> taskFrom;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "messageFromEmployees")
    private List<Messages> messageFrom;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Group> messageFor;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ContactUserId")
    private List<Contact> contactFor;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ContactReceivedId")
    private List<Contact> contactFrom;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestForEmployees")
    private List<Request> requestsFor;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "requestFromEmployees")
    private List<Request> requestsFrom;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Group> SetGroupsUser;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = GroupMessages.class, mappedBy = "messagesgroup")
    private List<GroupMessages> employeeMessagesGroup;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public Employee () {}

    public Employee(String username, String password, String firstName, String lastName) {
        this.username = username;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
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

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public List<Tasks> getTaskFor() {
        return taskFor;
    }

    public void setTaskFor(List<Tasks> taskFor) {
        this.taskFor = taskFor;
    }

    public List<Tasks> getTaskFrom() {
        return taskFrom;
    }

    public void setTaskFrom(List<Tasks> taskFrom) {
        this.taskFrom = taskFrom;
    }

    public List<Messages> getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(List<Messages> messageFrom) {
        this.messageFrom = messageFrom;
    }

    public List<Group> getMessageFor() {
        return messageFor;
    }

    public void setMessageFor(List<Group> messageFor) {
        this.messageFor = messageFor;
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

    public List<Request> getRequestsFor() {
        return requestsFor;
    }

    public void setRequestsFor(List<Request> requestsFor) {
        this.requestsFor = requestsFor;
    }

    public List<Request> getRequestsFrom() {
        return requestsFrom;
    }

    public void setRequestsFrom(List<Request> requestsFrom) {
        this.requestsFrom = requestsFrom;
    }

    public List<Group> getSetGroupsUser() {
        return SetGroupsUser;
    }

    public void setSetGroupsUser(List<Group> setGroupsUser) {
        SetGroupsUser = setGroupsUser;
    }

    public List<GroupMessages> getEmployeeMessagesGroup() {
        return employeeMessagesGroup;
    }

    public void setEmployeeMessagesGroup(List<GroupMessages> employeeMessagesGroup) {
        this.employeeMessagesGroup = employeeMessagesGroup;
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
}
