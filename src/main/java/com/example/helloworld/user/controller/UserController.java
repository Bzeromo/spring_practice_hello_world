package com.example.helloworld.user.controller;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.UserCreateDto2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // User information  초기화
    private final List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User("azeromo", "azero", "이영규", "azero@bzero.com", "2025-05-27"),
                    new User("bzeromo", "bzero", "박영규", "bzero@bzero.com", "2025-05-27"),
                    new User("czeromo", "czero", "김영규", "czero@bzero.com", "2025-05-27")
            )
    );

    /**
     * 모든 유저 조회
     * success: 200 code
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("getAllUsers 호출");
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

        Optional<User> userOpt = users.stream()
                .filter(user -> user.getUserId().equals(userid))
                .findFirst();

        // 200 or 404
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 유저 추가
     * success: 201 code
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("createUser 호출");

        users.add(user);
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
        Optional<User> userOpt = users.stream()
                .filter(usr -> usr.getUserId().equals(userid))
                .findFirst();

        if(userOpt.isPresent()) {
            User existUser = userOpt.get();
            users.remove(existUser);
            users.add(user);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 사용자 정보 중 이름, 이메일 수정
     * success: 200 code
     * failed: 404 code
     */
    @PatchMapping("/{userid}")
    public ResponseEntity<User> patchUser(@PathVariable String userid, @RequestBody User user) {
        log.info("patchUser 호출");
        Optional<User> userOpt = users.stream()
                .filter(usr -> usr.getUserId().equals(userid))
                .findFirst();

        if(userOpt.isPresent()) {
            User existUser = userOpt.get();
            users.remove(existUser);

            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());
            users.add(existUser);

            return ResponseEntity.ok(existUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 사용자 정보 삭제
     * success: 204 code
     * failed: 404 code
     */
    @DeleteMapping("/{userid}")
    public ResponseEntity<User> deleteUser(@PathVariable String userid) {
        log.info("deleteUser 호출");
        boolean removed = users.removeIf(
                user -> user.getUserId().equals(userid));

        if(removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 유저 추가 2 (유효성 검증 추가 버전)
     * 실제로 추가되진 않으며 유효성 검증 예시를 보여주기 위함으로서 return 값 역시 넣은 매개변수 그대로임
     * success: 201 code
     * fail: 400 code
     */
    @PostMapping("/valid")
    public ResponseEntity<?> createUser2(
            @Valid @RequestBody UserCreateDto2 user,
            BindingResult result) {
        log.info("createUser2 호출 : {}", user.toString());
        log.info("유효성 검사 메시지 : {}", result);

        if(result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
                log.info("errors : {}", errors);
            }

            return ResponseEntity.badRequest().body(errors);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
