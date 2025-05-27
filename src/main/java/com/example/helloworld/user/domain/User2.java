package com.example.helloworld.user.domain;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Data
//@Value
@Builder
@Slf4j
public class User2 {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String createdAt;

    public void testBuilder() {
        User2 user = User2.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .createdAt(createdAt)
                .build();
    }
}
