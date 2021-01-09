package com.example.MessangerServer.service;

import com.example.MessangerServer.model.Employees;
import com.example.MessangerServer.model.Role;
import com.example.MessangerServer.repository.EmployeesRepository;
import com.example.MessangerServer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employees empl = employeesRepository.findByUsername(username);

        if(empl == null){
            throw  new UsernameNotFoundException("Employee not found");
        }
        return empl;
    }

    public Employees findEmployeeById(Long userId){
        Optional<Employees> employeeFromDb = employeesRepository.findById(userId);
        return employeeFromDb.orElse(new Employees());
    }

    public List<Employees> allEmployees(){
        return employeesRepository.findAll();
    }

    public boolean saveUser(Employees employees){
        Employees  employeesFromDb = employeesRepository.findByUsername(employees.getUsername());

        if (employeesFromDb != null){
            return false;
        }
        employees.setRoles(Collections.singleton(new Role(1L,"ROLE_USER")));
        employees.setPassword(bCryptPasswordEncoder.encode(employees.getPassword()));
        employeesRepository.save(employees);
        return true;
    }

    public boolean deleteEmployee(Long employeeId){
        if (employeesRepository.findById(employeeId).isPresent()){
            employeesRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }
    public List<Employees> emplgtList(Long idMin) {
        return entityManager.createQuery("SELECT u FROM EMPLOYEES u WHERE u.id > :paramId", Employees.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
