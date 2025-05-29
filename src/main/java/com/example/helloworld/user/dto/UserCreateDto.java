package com.example.helloworld.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    private String userId;
    private String password;
    private String name;
    private String email;
}
