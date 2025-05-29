package com.example.helloworld.user.service;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.dto.UserCreateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface UserService {

    ArrayList<FindAllUserDto> readAllUser();
    User readByUserId(String userId);
    boolean newUser(UserCreateDto user);
}
