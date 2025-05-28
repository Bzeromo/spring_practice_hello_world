package com.example.helloworld.user;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor // final field 변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceTest {

    private final UserService userService;

    @Test
    @DisplayName("사용자 전체 조회 테스트: service")
    public void readAllUserTest() {
        //Given
        //When
        ArrayList<FindAllUserDto> users = userService.readAllUser();
        //Then
        log.info("users = {}", users);
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("특정 사용자 조회 테스트: service")
    public void readByUserIdTest() {
        //Given
        String userId = "bzeromo";
        //When
        User user = userService.readByUserId(userId);
        //Then
        log.info("users = {}", user);
        assertThat(user).isNotNull();
    }
}
