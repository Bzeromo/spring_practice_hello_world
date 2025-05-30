package com.example.helloworld.user.controller;

import com.example.helloworld.user.dto.UserCreateDto2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v4")
public class UserController4 {

    @GetMapping("/newUser2")
    public String newUser(Model model) {
        model.addAttribute("userDto", new UserCreateDto2());

        return "views/newUser2";
    }

    @PostMapping("/newUser2")
    public String newUserOk(@Valid @ModelAttribute("userDto") UserCreateDto2 user, BindingResult result, Model model) {
        log.info("전송된 사용자 정보 : {}", user);
        log.info("bindingResult : {}", result);

        if(result.hasErrors()) {
            //model.addAttribute("userDto", user);

            return "views/newUser2";
        }

        return "views/newUserOk";
    }
}
