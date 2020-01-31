package com.example.vue.domain.auth;

import com.example.vue.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        if (userRepository.findByEmail(email).size() < 1) {
            throw new AuthException.NoExistEmail(email);
        }
    }
}
