package com.example.vue.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping(value = "/register")
    public void register() {

    }
}
