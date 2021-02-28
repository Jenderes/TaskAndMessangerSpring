package com.example.MessangerServer.service;

import com.example.MessangerServer.model.Employee;

import java.util.List;

public interface UserService {
     Employee register(Employee employee);

     List<Employee> getAll();

     Employee findByUsername(String username);

     boolean existsByUsername(String username);

     Employee findById(Long id);

     void delete(Long id);
}
