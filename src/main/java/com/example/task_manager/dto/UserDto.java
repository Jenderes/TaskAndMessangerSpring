package com.example.task_manager.dto;

import com.example.task_manager.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;

    public Employee toEmployee(){
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }
    public static UserDto fromEmployee(Employee employee){
        UserDto userDto = new UserDto();
        userDto.setId(employee.getUserId());
        userDto.setUsername(employee.getUsername());
        userDto.setFirstName(employee.getFirstName());
        userDto.setLastName(employee.getLastName());
        return userDto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
