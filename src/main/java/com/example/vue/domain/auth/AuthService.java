package com.example.vue.domain.auth;

import com.example.vue.domain.user.User;
import com.example.vue.domain.user.UserRepository;
import com.example.vue.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        List<User> users = userRepository.findByEmail(email);
        if (users.size() < 1) {
            throw new AuthException.NoExistEmail(email);
        }

        User user = users.get(0);

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new AuthException.PasswordNotMatched();
        }

        String token = jwtUtil.createToken(user.getId(), user.getName(), "ROLE_USER");
        return new LoginResponseDto(token);
    }
}
