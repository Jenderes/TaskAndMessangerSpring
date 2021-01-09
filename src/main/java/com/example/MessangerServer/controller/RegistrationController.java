package com.example.MessangerServer.controller;

import com.example.MessangerServer.model.Employees;
import com.example.MessangerServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registation(Model model){
        model.addAttribute("employeeFrom", new Employees());

        return "registration";
    }

    @PostMapping("/registration")
    public String addEmployee( @ModelAttribute("employeeFrom") @Valid Employees employeeForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if (!employeeForm.getPassword().equals(employeeForm.getPasswordConfirm())){
            model.addAttribute("passwordError","Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(employeeForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }
}

