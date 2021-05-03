package com.example.task_manager.security.jwt;

import com.example.task_manager.model.Employee;
import com.example.task_manager.model.Role;
import com.example.task_manager.model.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }
    public static JwtUser create(Employee employee){
        return new JwtUser(
                employee.getUserId(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getStatus().equals(Status.ACTIVE),
                mapToGrantedAuthorities(new ArrayList<>(employee.getRoles()))
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> employeeRoles){
        return employeeRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
