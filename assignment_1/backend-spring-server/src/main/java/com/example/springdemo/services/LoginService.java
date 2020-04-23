package com.example.springdemo.services;

import com.example.springdemo.dto.UserDTO;
import com.example.springdemo.dto.builders.UserMapper;
import com.example.springdemo.entities.User;
import com.example.springdemo.utils.UsersDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsersDetailsService usersDetailsService;

    public UserDTO getLoggedUser(){
        User user = usersDetailsService.loadCurrentUser();
        return UserMapper.generateUserDTOFromUser(user);
    }
}
