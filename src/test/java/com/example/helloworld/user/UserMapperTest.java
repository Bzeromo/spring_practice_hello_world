package com.example.helloworld.user;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@MybatisTest
@RequiredArgsConstructor // final field 변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserMapperTest {

    // 생성자를 이용한 의존성 주입
    private final UserRepository userRepository;

    @Test
    @DisplayName("사용자 전체 조회 테스트")
    void findAllUsersTest() {
        // Given
        // When
        List<User> users = userRepository.findAllUser();
        // Then
        log.info("users = {}", users);
        assertThat(users.size()).isGreaterThan(0);
    }
}
