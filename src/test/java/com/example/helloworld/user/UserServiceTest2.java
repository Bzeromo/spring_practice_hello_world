package com.example.helloworld.user;

import com.example.helloworld.user.domain.User;
import com.example.helloworld.user.dto.FindAllUserDto;
import com.example.helloworld.user.repository.UserRepository;
import com.example.helloworld.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class) // 단위 테스트: service
public class UserServiceTest2 {

    @Mock // 모의 UserRepository 객체
    private UserRepository userRepository;

    @InjectMocks // 모의 객체로 생성시 대상은 클래스 유형으로 지정
    private UserServiceImpl userServiceImpl;

    @Test
    @DisplayName("사용자 전체 조회 테스트: service2")
    public void readAllUserTest() {
        //Given
        List<FindAllUserDto> users = new ArrayList<>(
                Arrays.asList(
                        new FindAllUserDto("ezeromo", "azero@bzero.com", "2025-05-27"),
                        new FindAllUserDto("fzeromo", "bzero@bzero.com", "2025-05-27"),
                        new FindAllUserDto("gzeromo", "czero@bzero.com", "2025-05-27")
                )
        );

        when(userRepository.findAllUser()).thenReturn(users);

        //When
        ArrayList<FindAllUserDto> result = userServiceImpl.readAllUser();

        //Then
        assertThat(result).isNotNull();
        assertThat(users.size()).isEqualTo(result.size());
        assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("특정 사용자 조회 테스트: service2")
    public void readByUserIdTest() {
        //Given
        String userId = "dzeromo";
        User user = new User("dzeromo", "dzero", "도영규", "dzero@bzero.com", "2025-05-28");
        given(userRepository.findByUserId(userId)).willReturn(user);
        //when(userRepository.findByUserId(userId)).thenReturn(user);

        //When
        User result = userServiceImpl.readByUserId(userId);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getName()).isEqualTo("도영규");
        assertThat(result.getPassword()).isEqualTo("dzero");
    }
}
