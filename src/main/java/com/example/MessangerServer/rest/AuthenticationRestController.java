package com.example.MessangerServer.rest;

import com.example.MessangerServer.dto.RegisterDto;
import com.example.MessangerServer.security.jwt.JwtTokenProvider;
import com.example.MessangerServer.dto.AuthenticationRequestDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Employee employee = userService.findByUsername(username);

            if (employee == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String token = jwtTokenProvider.createToken(username, employee.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationServiceException exp) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        try {
            userService.register(new Employee(
                    registerDto.getUsername(),
                    registerDto.getPassword(),
                    registerDto.getFirstName(),
                    registerDto.getLastName()
            ));
            String username = registerDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, registerDto.getPassword()));
            Employee employee = userService.findByUsername(username);
            if (employee == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String token = jwtTokenProvider.createToken(username, employee.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationServiceException exp) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
