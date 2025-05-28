package com.example.helloworld.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor // final field 변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserControllerTest {

    private final MockMvc mockMvc;

    @Test
    @DisplayName("사용자 전체 조회 테스트: controller")
    public void getUsers() throws Exception {
        // Given
        // When
        mockMvc.perform(get("/api/v2/users"))
                .andExpect(status().isOk());
        // Then

    }

    @Test
    @DisplayName("사용자 조회 테스트: controller")
    public void getUserById() throws Exception {
        // Given
        String userId = "bzeromo";
        // When
        mockMvc.perform(get("/api/v2/users/" +userId)
                .param("userid", userId))
                .andExpect(status().isOk());
        // Then
    }
}
