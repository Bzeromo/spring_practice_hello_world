package com.example.helloworld.user;

import com.example.helloworld.user.dto.UserCreateDto2;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class UserDtoValidTest {

    private final Validator validator;

    @Autowired
    public UserDtoValidTest(Validator validator) {
        this.validator = validator;
    }

    @Test
    @DisplayName("모든 항목에 대한 유효성 검사")
    void validateAllFields() {
        UserCreateDto2 user = createDefaultDto();

        // 유효성 검사 실시 후 위반사항 받아오기
        Set<?> violations = validator.validate(user);

        log.info("violations: {}", violations);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("일부 항목에 대한 유효성 검사")
    void validatePartialField() {
        UserCreateDto2 user = createDefaultDto();
        user.setEmail(null);
        user.setPassword(null);

        // 유효성 검사 실시 후 위반사항 받아오기
        Set<?> violations = validator.validate(user);

        log.info("violations: {}", violations);
        assertThat(violations).hasSize(2);
    }

    // Helper Method
    private UserCreateDto2 createDefaultDto() {
//        UserCreateDto2 user = new UserCreateDto2();
//        user.setUserId("azero123");
//        user.setPassword("azero123");
//        user.setEmail("azero123@google.com");
//        user.setName("박영모");
//        user.setPhone("010-2222-2222");

        return UserCreateDto2.builder()
                .userId("azeromo123")
                .password("azeromo123")
                .email("azeromo123@google.com")
                .phone("010-2222-2222")
                .name("박영모")
                .build();
    }
}
