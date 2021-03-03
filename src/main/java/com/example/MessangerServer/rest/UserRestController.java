package com.example.MessangerServer.rest;

import com.example.MessangerServer.security.jwt.JwtTokenProvider;
import com.example.MessangerServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
@Slf4j
public class UserRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public UserRestController(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("contacts")
    public ResponseEntity getListContacts(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        log.info("get username from token - username: {}",username);
        List<Object[]> response = userService.findFullNameByUsername(username);
        log.info("list employee: "+ response.toString());
        return ResponseEntity.ok(response);
    }

}
