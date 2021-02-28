package com.example.MessangerServer.security;

import com.example.MessangerServer.security.jwt.JwtUserFactory;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// используется чтобы создать UserDetails объект путем реализации метода интерфейса
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = userService.findByUsername(username);

        if (employee == null){
            throw new UsernameNotFoundException("Employee with username: " +username+ " not found");
        }
        return JwtUserFactory.create(employee);
    }
}
