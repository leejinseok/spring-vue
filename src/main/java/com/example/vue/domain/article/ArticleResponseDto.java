package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import com.example.vue.domain.user.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;
    private UserResponseDto user;

    @JsonProperty("isOwn")
    private boolean isOwn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDto(Article article, User user) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.isOwn = article.compareUser(user);
        this.user = new UserResponseDto(article.getUser());
    }
}
