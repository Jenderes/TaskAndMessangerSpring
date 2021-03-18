package com.example.MessangerServer.service.impl;

import com.example.MessangerServer.model.*;
import com.example.MessangerServer.repository.ContactsRepository;
import com.example.MessangerServer.repository.EmployeeRepository;
import com.example.MessangerServer.repository.RoleRepository;
import com.example.MessangerServer.repository.TasksRepository;
import com.example.MessangerServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository  roleRepository;
    private final ContactsRepository contactsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(EmployeeRepository employeeRepository, RoleRepository roleRepository, ContactsRepository contactsRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.contactsRepository = contactsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Employee register(Employee employee){
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> employeeRole = new ArrayList<>();
        employeeRole.add(role);
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
    public boolean existsByEmail(String email) {
        boolean result = employeeRepository.existsByEmail(email);
        log.info("IN existsByEmail: {} foundByUsername: {}",result,email);
        return employeeRepository.existsByUsername(email);
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
    public List<Employee> findContactsByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        log.info("IN findByUsername - employee: {} found by username : {}",employee,username);
        List<Contact> allContacts = Stream.concat(employee.getContactFor().stream(),employee.getContactFrom().stream()).distinct().collect(Collectors.toList());
        List<Employee> ContacGet = allContacts.stream().map(Contact::getContactReceivedId).filter(employees -> !employees.getUsername().equals(username)).collect(Collectors.toList());
        List<Employee> ContactSet = allContacts.stream().map(Contact::getContactUserId).filter(employees -> !employees.getUsername().equals(username)).collect(Collectors.toList());
        return Stream.concat(ContacGet.stream(),ContactSet.stream()).distinct().collect(Collectors.toList());
    }

    @Override
    public void saveContactFromUser(Employee employeeOwn, Employee employeeContact) {
        Contact contact = new Contact(Status.COMPLETE, employeeOwn, employeeContact);
        contactsRepository.save(contact);
    }

}
