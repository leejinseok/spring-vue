package com.example.vue.domain.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

}
