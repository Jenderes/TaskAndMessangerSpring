package com.example.MessangerServer.service.impl;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Role;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.model.Tasks;
import com.example.MessangerServer.repository.EmployeeRepository;
import com.example.MessangerServer.repository.RoleRepository;
import com.example.MessangerServer.repository.TasksRepository;
import com.example.MessangerServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository  roleRepository;
    private final TasksRepository tasksRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, TasksRepository tasksRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.tasksRepository = tasksRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Employee register(Employee employee){
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> employeeRole = new ArrayList<>();
        employeeRole.add(role);

        Employee employeeCheckUsername = employeeRepository.findByUsername(employee.getUsername());
        if (employeeCheckUsername != null) {
            return null;
        }
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setRoles(employeeRole);
        employee.setStatus(Status.ACTIVE);
        Employee registerEmployee = employeeRepository.save(employee);
        log.info("IN register - user: {} successfully registered", registerEmployee);
        return registerEmployee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> result = employeeRepository.findAll();
        log.info("IN getAll - {} employee fount", result);
        return result;
    }

    @Override
    public Employee findByUsername(String username) {
        Employee result = employeeRepository.findByUsername(username);
        log.info("IN findByUsername - employee: {} found by username : {}",result,username);
        return result;
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean result = employeeRepository.existsByUsername(username);
        log.info("IN existsByUsername: {} foundByUsername: {}",result,username);
        return employeeRepository.existsByUsername(username);
    }
    
    @Override
    public Employee findById(Long id) {
        Employee result = employeeRepository.findByUserId(id);
        log.info("IN findById - employee: {} foundById : {}",result,id);
        return result;
    }

    @Override
    public void delete(Long id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            log.info("IN delete - user with id: {} successfully deleted",id);
        }
    }

    @Override
    public List<Object[]> findFullNameByUsername(String username) {
        List<Object[]> contacts = employeeRepository.getEmployeeListFullNameContact(username);
        log.info("IN getEmployeeListFullNameContact - list: {} foundByUsername: {}",contacts,username);
        return contacts;
    }

    @Override
    public List<Tasks> findTaskSendByUsername(String username) {
        Employee empl = findByUsername(username);
        List<Tasks> taskList = empl.getTaskFrom();
        log.info("IN findTaskSendByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }

    @Override
    public List<Tasks> findTaskGetByUsername(String username) {
        Employee empl = findByUsername(username);
        List<Tasks> taskList = empl.getTaskFor();
        log.info("IN findTaskGetByUsername - List: {} By username: {}",taskList.toString(),username);
        return taskList;
    }
}
