package com.example.vue.domain.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
