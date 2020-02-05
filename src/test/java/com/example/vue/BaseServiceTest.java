package com.example.vue;

import com.example.vue.domain.article.Article;
import com.example.vue.domain.article.ArticleRepository;
import com.example.vue.domain.user.User;
import com.example.vue.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseServiceTest {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public User findTestUser() {
        return userRepository.findAll(PageRequest.of(0, 1)).get(0);
    }

    public Article findTestArticle() {
        return articleRepository.findAll(PageRequest.of(0, 1)).get(0);
    }
}
