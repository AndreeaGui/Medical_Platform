package com.example.springdemo.controller;

import com.example.springdemo.dto.UserDTO;
import com.example.springdemo.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/getUser")
    public UserDTO getLoggedUser(){

        System.out.println(loginService.getLoggedUser().getRole());
        return loginService.getLoggedUser();
    }

}
