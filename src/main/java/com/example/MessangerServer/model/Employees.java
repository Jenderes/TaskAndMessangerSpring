package com.example.MessangerServer.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "EMPLOYEES")
public class Employees implements UserDetails {
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
    @Temporal (TemporalType.DATE)
    private Date leaveDate;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "taskForEmployees")
    private Set<Tasks> taskFor;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "taskFromEmployees")
    private Set<Tasks> taskFrom;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "messageForEmployees")
    private Set<Messages> messageFor;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "messageFromEmployees")
    private Set<Messages> messageFrom;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "contactForEmployees")
    private Set<Contacts> contactFor;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "contactFromEmployees")
    private Set<Contacts> contactFrom;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Groups> listGroupsUser;
    @OneToMany(targetEntity=GroupMessages.class, mappedBy="messagesgroup")
    private Set<GroupMessages> employeeMessagesGroup;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Messages> getMessageFor() {
        return messageFor;
    }

    public void setMessageFor(Set<Messages> messageFor) {
        this.messageFor = messageFor;
    }

    public Set<Messages> getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(Set<Messages> messageFrom) {
        this.messageFrom = messageFrom;
    }

    public Set<Tasks> getTaskFor() {
        return taskFor;
    }

    public void setTaskFor(Set<Tasks> taskFor) {
        this.taskFor = taskFor;
    }

    public Set<Tasks> getTaskFrom() {
        return taskFrom;
    }

    public void setTaskFrom(Set<Tasks> taskFrom) {
        this.taskFrom = taskFrom;
    }

    public Set<Contacts> getContactFor() {
        return contactFor;
    }

    public void setContactFor(Set<Contacts> contactFor) {
        this.contactFor = contactFor;
    }

    public Set<Contacts> getContactFrom() {
        return contactFrom;
    }

    public void setContactFrom(Set<Contacts> contactFrom) {
        this.contactFrom = contactFrom;
    }

    public Set<Groups> getListGroupsUser() {
        return listGroupsUser;
    }

    public void setListGroupsUser(Set<Groups> listGroupsUser) {
        this.listGroupsUser = listGroupsUser;
    }

    public Set<GroupMessages> getEmployeeMessagesGroup() {
        return employeeMessagesGroup;
    }

    public void setEmployeeMessagesGroup(Set<GroupMessages> employeeMessagesGroup) {
        this.employeeMessagesGroup = employeeMessagesGroup;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
