package com.example.task_manager.rest;

import com.example.task_manager.dto.ContactsDto;
import com.example.task_manager.dto.MessageDto;
import com.example.task_manager.dto.ProfileDto;
import com.example.task_manager.dto.StatisticDto;
import com.example.task_manager.model.Employee;
import com.example.task_manager.security.jwt.JwtTokenProvider;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.service.UserService;
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
    public List<ContactsDto> getListContacts(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        log.info("get token from request");
        String username = jwtTokenProvider.getUserName(resolveToken);
        log.info("get username from token - username: {}",username);
        List<Employee>  empl = userService.findContactsByUsername(username);
        List<ContactsDto> contactsDto = userService.findContactsByUsername(username).stream().map(employee -> new ContactsDto(
                employee.getUserId(),
                employee.getFirstName(),
                employee.getLastName()
        )).collect(Collectors.toList());
        return contactsDto;
    }

    @PostMapping("contacts/add/{id}")
    public ResponseEntity<?> setContacts(HttpServletRequest request, @PathVariable(name = "id") Long id){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        Employee employeeOwn = userService.findByUsername(username);
        Employee employeeContact = userService.findById(id);
        userService.saveContactFromUser(employeeOwn, employeeContact);
        return ResponseEntity.ok(new MessageDto("contact added"));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> OpenPublicUserProfile(@PathVariable(name = "id") Long id){
        Employee employee = userService.findById(id);
        ProfileDto profileDto = new ProfileDto(
                employee.getUsername(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return ResponseEntity.ok(profileDto);
    }

    @GetMapping("statistic")
    public ResponseEntity<?> getStatistic(HttpServletRequest request){
        String resolveToken = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUserName(resolveToken);
        StatisticDto statisticDto = taskService.findCountTaskByUsername(username);
        return ResponseEntity.ok(statisticDto);
    }

}
