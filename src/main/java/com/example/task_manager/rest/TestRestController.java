package com.example.task_manager.rest;

import com.example.task_manager.dto.ProjectCreateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/test/")
public class TestRestController {

    @PostMapping("create")
    public ResponseEntity<?> sendList(HttpServletRequest request,
                                      @RequestBody ProjectCreateDto projectCreateDTO) {
        System.out.println(projectCreateDTO);
        return ResponseEntity.ok("data transfer");
    }
}
