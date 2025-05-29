package com.example.helloworld.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor // final field 변수로 생성자 생성
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
public class UserControllerTest {

    private final MockMvc mockMvc;

    @Test
    @DisplayName("사용자 전체 조회 테스트: controller")
    public void getUsers() throws Exception {
        // Given
        // When
        mockMvc.perform(get("/api/v2/users")
                        .contentType(MediaType.APPLICATION_JSON)) // 응답 컨텐츠 유형 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());
        // Then
    }

    @Test
    @DisplayName("사용자 조회 테스트: controller")
    public void getUserById() throws Exception {
        // Given
        String userid = "bzeromo";
        // When
        mockMvc.perform(get("/api/v2/users/{userid}", userid)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userid))
                .andDo(print());
        // Then
    }
}
