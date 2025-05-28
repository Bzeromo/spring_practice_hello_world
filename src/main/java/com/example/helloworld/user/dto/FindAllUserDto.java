package com.example.helloworld.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindAllUserDto {

    private String userId;
    private String email;
    private String createdAt;

}
