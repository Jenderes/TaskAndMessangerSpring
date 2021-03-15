package com.example.MessangerServer.rest;

import com.example.MessangerServer.dto.UserDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

        private final UserService userService;

        @Autowired
        public AdminRestController(UserService userService) {
            this.userService = userService;
        }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        Employee employee = userService.findById(id);

        if (employee == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromEmployee(employee);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
