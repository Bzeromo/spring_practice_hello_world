package com.example.helloworld.user.service;

import com.example.helloworld.user.domain.User3;
import com.example.helloworld.user.repository.UserRepository5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service // IoC 관리 대상 지정
@RequiredArgsConstructor
public class UserServiceImpl5 implements UserService5 {

    private final UserRepository5 userRepository;

    @Override
    public List<User3> readAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User3> readByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User3 newUser(User3 user) {
        return userRepository.save(user);
    }
}
