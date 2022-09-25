package com.springsecurity.spsecurity.controller;

import com.springsecurity.spsecurity.dto.UserRegistrationDto;
import com.springsecurity.spsecurity.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController
{
    private final UserServiceImpl userService;

    public UserRegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showeRegistrationForm(Model model)
    {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto )
    {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }

}
