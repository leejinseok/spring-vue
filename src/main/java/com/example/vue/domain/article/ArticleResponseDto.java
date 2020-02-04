package com.example.vue.domain.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String content;

    @JsonProperty("isOwn")
    private boolean isOwn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
