package com.example.vue.user;

import com.example.vue.BaseServiceTest;
import com.example.vue.domain.article.ArticleService;
import com.example.vue.domain.auth.AuthService;
import com.example.vue.domain.auth.RegisterRequestDto;
import com.example.vue.domain.user.UserResponseDto;
import com.example.vue.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private BaseServiceTest baseTest;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Test
    @Transactional
    public void registerTest() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();
        registerRequestDto.setEmail("b@b.com");
        registerRequestDto.setPassword("1111");
        UserResponseDto userResponseDto = authService.register(registerRequestDto);
    }

}
