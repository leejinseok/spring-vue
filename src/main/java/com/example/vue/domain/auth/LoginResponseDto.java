package com.example.vue.domain.auth;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }
}
