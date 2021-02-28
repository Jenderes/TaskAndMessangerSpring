package com.example.MessangerServer.service;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Role;
import com.example.MessangerServer.model.Status;
import com.example.MessangerServer.repository.EmployeeRepository;
import com.example.MessangerServer.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
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

    // Добавить логирование на проверку удаления
    @Override
    public Employee findById(Long id) {
        Employee result = findById(id);
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
}
