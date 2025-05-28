package com.example.helloworld.user.controller;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController2 {

    private final UserService userService;

    /**
     * 모든 유저 조회
     * success: 200 code
     */
    @GetMapping
    public ResponseEntity<List<FindAllUserDto>> getAllUsers() {
        log.info("getAllUsers 호출");

        List<FindAllUserDto> users = userService.readAllUser();

        return ResponseEntity.ok(users);
    }

    /**
     * 특정 id로 식별 - 경로변수로 사용자 검색
     * success: 200 code
     * failed: 404 code
     */
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable String userid) {
        log.info("getUserById 호출");

        User user = userService.readByUserId(userid);

        // 200 or 404
        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
}
