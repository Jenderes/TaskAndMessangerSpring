package com.example.MessangerServer.service;

import com.example.MessangerServer.dto.StatisticDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Tasks;

import java.util.List;
import java.util.Map;

public interface UserService {
     Employee register(Employee employee);

     List<Employee> getAll();

     Employee findByUsername(String username);

     boolean existsByUsername(String username);

     boolean existsByEmail(String email);

     Employee findById(Long id);

     void delete(Long id);

     List<Employee> findContactsByUsername(String username);
}
