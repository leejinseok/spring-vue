package com.example.vue.user;

import com.example.vue.domain.article.ArticleException;

public class UserServiceException {

    public static class NoExist extends RuntimeException {
        public NoExist() {
            super("사용자가 존재하지 않습니다.");
        }
    }
}
