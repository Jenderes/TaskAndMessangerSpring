package com.example.MessangerServer.rest;

import com.example.MessangerServer.dto.ContactsDto;
import com.example.MessangerServer.dto.ProfileDto;
import com.example.MessangerServer.dto.StatisticDto;
import com.example.MessangerServer.model.Employee;
import com.example.MessangerServer.security.jwt.JwtTokenProvider;
import com.example.MessangerServer.service.TaskService;
import com.example.MessangerServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/user/")
@Slf4j
public class UserRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public UserRestController(JwtTokenProvider jwtTokenProvider, UserService userService, TaskService taskService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("contacts")
    public ResponseEntity<?> getListContacts(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        log.info("get username from token - username: {}",username);
        List<ContactsDto> contactsDto = userService.findContactsByUsername(username).stream().map(employee -> new ContactsDto(
                employee.getUserId(),
                employee.getFirstName(),
                employee.getLastName()
        )).collect(Collectors.toList());
        return ResponseEntity.ok(contactsDto);
    }
    @GetMapping("contacts/add/{id}")
    public ResponseEntity<?> setContacts(HttpServletRequest request, @PathVariable(name = "id") Long id){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee employeeOwn = userService.findByUsername(username);
        Employee employeeContact = userService.findById(id);
        userService.saveContactFromUser(employeeOwn, employeeContact);
        return ResponseEntity.ok("contact added");
    }
    @GetMapping("{id}")
    public ResponseEntity<?> OpenPublicUserProfile(@PathVariable(name = "id") Long id){
        Employee empl = userService.findById(id);
        return ResponseEntity.ok(new ProfileDto(
             empl.getFirstName(),
             empl.getLastName(),
             empl.getEmail()
        ));
    }
    @GetMapping("statistic")
    public ResponseEntity<?> getStatistic(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        StatisticDto statisticDto = taskService.findCountTaskByUsername(username);
        return ResponseEntity.ok(statisticDto);
    }

}
