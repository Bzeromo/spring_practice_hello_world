package com.example.helloworld.user.controller;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.dto.UserCreateDto;
import com.example.helloworld.user.service.UserService;
import com.example.helloworld.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v3")
public class UserController3 {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    /**
     * (url에 동사를 넣는건 REST하지 못한 방식이지만 학습용이니 그러려니 하세용)
     * 모든 유저 조회 후 view로 띄우기
     */
    @GetMapping("/getUsers")
    public String getUsers(Model model) {
        log.info("getUsers 호출");

        ArrayList<FindAllUserDto> users = userService.readAllUser();
        model.addAttribute("users", users);

        return "views/listUser";
    }

    /**
     * (url에 동사를 넣는건 REST하지 못한 방식이지만 학습용이니 그러려니 하세용)
     * id로 유저 조회 후 view로 띄우기
     */
    @GetMapping({"/getUser/{userid}", "/getUser"})
    public String getUser(@PathVariable("userid") Optional<String> userid, Model model) {
        log.info("getUser 호출");

        User user = userService.readByUserId(userid.orElse("azeromo"));
        model.addAttribute("user", user);

        return "views/getUser";
    }

    @GetMapping("/newUser")
    public String newUser() {

        return "views/newUser";
    }

    @PostMapping("/newUser")
    public String newUserOk(UserCreateDto user, Model model) {
        log.info("전송된 사용자 정보 : {}", user);

        if(userServiceImpl.newUser(user)) {
            model.addAttribute("user", user);
            model.addAttribute("msg", "사용자 등록 성공");
        } else {
            model.addAttribute("msg", "사용자 등록 실패");
        }

        return "views/newUserOk";
    }
}
