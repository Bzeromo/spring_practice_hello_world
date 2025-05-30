package com.example.helloworld.user.controller;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.domain.User3;
import com.example.helloworld.user.service.UserService5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/users")
public class UserController5 {

    private final UserService5 userService5;

    /**
     * 모든 유저 조회
     * success: 200 code
     */
    @GetMapping
    public ResponseEntity<List<User3>> getAllUsers() {
        log.info("getAllUsers 호출");

        List<User3> users = userService5.readAllUser();

        return ResponseEntity.ok(users);
    }

    /**
     * 특정 id로 식별 - 경로변수로 사용자 검색
     * success: 200 code
     * failed: 404 code
     */
    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable String userid) {
        log.info("getUserById 호출");

        Optional <User3> user = userService5.readByUserId(userid);

        return ResponseEntity.ok(user);
    }

    /**
     * 유저 추가
     * success: 201 code
     */
    @PostMapping
    public ResponseEntity<User3> createUser(@RequestBody User3 userParam) {
        log.info("createUser 호출");

        User3 user = userService5.newUser(userParam);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * 사용자 정보 전체 수정
     * success: 200 code
     * failed: 404 code
     */
    @PutMapping("/{userid}")
    public ResponseEntity<User> modifyUser(@PathVariable String userid, @RequestBody User user) {
        log.info("modifyUser 호출");

        return ResponseEntity.ok(null);
    }

    /**
     * 사용자 정보 중 이름, 이메일 수정
     * success: 200 code
     * failed: 404 code
     */
    @PatchMapping("/{userid}")
    public ResponseEntity<User> patchUser(@PathVariable String userid, @RequestBody User user) {
        log.info("patchUser 호출");

        return ResponseEntity.ok(null);
    }

    /**
     * 사용자 정보 삭제
     * success: 204 code
     * failed: 404 code
     */
    @DeleteMapping("/{userid}")
    public ResponseEntity<User> deleteUser(@PathVariable String userid) {
        log.info("deleteUser 호출");

        return ResponseEntity.ok(null);
    }

}
