package com.example.helloworld.user.service;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.dto.UserCreateDto;
import com.example.helloworld.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service // IoC 관리 대상 지정
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ArrayList<FindAllUserDto> readAllUser() {
        return (ArrayList<FindAllUserDto>) userRepository.findAllUser();
    }

    @Override
    public User readByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public boolean newUser(UserCreateDto user) {
        return userRepository.insertUser(user) > 0;
    }
}
