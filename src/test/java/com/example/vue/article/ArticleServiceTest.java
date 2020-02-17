package com.example.vue.article;

import com.example.vue.BaseServiceTest;
import com.example.vue.domain.article.Article;
import com.example.vue.domain.article.ArticleRequestDto;
import com.example.vue.domain.article.ArticleService;
import com.example.vue.domain.user.User;
import com.example.vue.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private BaseServiceTest baseTest;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void postArticle() {
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setTitle("title");
        articleRequestDto.setTitle("content");
        User user = baseTest.findTestUser();

        articleService.save(articleRequestDto, user);
    }

    @Test
    @Transactional
    public void putArticle() {
        Article article = baseTest.findTestArticle();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setTitle("update title");
        articleRequestDto.setContent("update content");

        articleService.update(article.getId(), articleRequestDto, baseTest.findTestUser());
    }

    @Test
    public void getArticle() {
        User user = baseTest.findTestUser();

        Long articleId = baseTest.findTestArticle().getId();
        articleService.findById(articleId, user);
    }

    @Test
    public void getArticles() {
        articleService.findAll(PageRequest.of(0, 10), baseTest.findTestUser());
    }

}
