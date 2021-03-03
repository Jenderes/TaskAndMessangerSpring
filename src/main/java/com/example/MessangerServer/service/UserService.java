package com.example.MessangerServer.service;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Tasks;

import java.util.List;

public interface UserService {
     Employee register(Employee employee);

     List<Employee> getAll();

     Employee findByUsername(String username);

     boolean existsByUsername(String username);

     Employee findById(Long id);

     void delete(Long id);

     List<Object[]> findFullNameByUsername(String username);

     List<Tasks> findTaskSendByUsername(String username);

     List<Tasks> findTaskGetByUsername(String username);
}
