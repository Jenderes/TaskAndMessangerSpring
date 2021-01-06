package com.example.MessangerServer.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull
    private String login;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String model;
    private String function;
    private String phone;
    @OneToMany(targetEntity=Messages.class, mappedBy="messages")
    private List<Messages> messages = new ArrayList<>();
    @OneToMany(targetEntity=Tasks.class, mappedBy="tasks")
    private List<Tasks> tasks = new ArrayList<>();
    @OneToMany(targetEntity=Contacts.class, mappedBy="contacts")
    private List<Contacts> contacts = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Groups> listGroupsUser;
    @OneToMany(targetEntity=GroupMessages.class, mappedBy="groupmessages")
    private List<GroupMessages> employeeMessagesGroup = new ArrayList<>();
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
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

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public List<Groups> getListGroupsUser() {
        return listGroupsUser;
    }

    public void setListGroupsUser(List<Groups> listGroupsUser) {
        this.listGroupsUser = listGroupsUser;
    }

    public List<GroupMessages> getEmployeeMessagesGroup() {
        return employeeMessagesGroup;
    }

    public void setEmployeeMessagesGroup(List<GroupMessages> employeeMessagesGroup) {
        this.employeeMessagesGroup = employeeMessagesGroup;
    }
}
