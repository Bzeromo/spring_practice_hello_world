package com.example.helloworld.user.service;

import com.example.helloworld.user.domain.User3;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService5 {

    List<User3> readAllUser();
    Optional<User3> readByUserId(String userId);
    User3 newUser(User3 user);
}
