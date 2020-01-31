package com.example.vue.domain.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class NoExistEmail extends RuntimeException {
        public NoExistEmail(String email) {
            super("존재하지 않는 이메일입니다. [email=" + email + "]");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class PasswordNotMatched extends RuntimeException {
        public PasswordNotMatched() {
            super("패스워드가 일치하지 않습니다.");
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class AlreadyExist extends RuntimeException {
        public AlreadyExist(String email) {
            super("이미 존재하는 사용자입니다. [email=" + email + "]");
        }
    }
}
