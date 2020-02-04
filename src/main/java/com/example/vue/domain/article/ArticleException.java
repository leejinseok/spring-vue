package com.example.vue.domain.article;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

public class ArticleException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class NoExist extends RuntimeException {
        public NoExist(Long id) {
            super("존재하지 않는 게시글입니다. [articleId=" + id + "]");
        }
    }

    public static Supplier<NoExist> passNoExistException(Long id) {
        return () -> new NoExist(id);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public static class AccessNotOwned extends RuntimeException {
        public AccessNotOwned(Long id) {
            super("사용자에게 권한이 없습니다.");
        }
    }
}
