package com.example.vue.domain.user;

import java.util.function.Supplier;

public class UserException {

    public static class NoExist extends RuntimeException {
        public NoExist(Long id) {
            super("존재하지 않는 사용자입니다. [userId=" + id + "]");
        }
    }

    public static Supplier<NoExist> passNoExistExceptionSupplier(Long id) {
        return () -> new NoExist(id);
    }
}
