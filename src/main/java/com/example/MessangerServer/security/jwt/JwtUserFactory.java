package com.example.MessangerServer.security.jwt;

import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.model.Role;
import com.example.MessangerServer.model.Status;
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
                employee.getPassword(),
                employee.getStatus().equals(Status.ACTIVE),
                mapToGrantedAuthorities(new ArrayList<>(employee.getRoles()))
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> employeeRoles){
        return employeeRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }
}
