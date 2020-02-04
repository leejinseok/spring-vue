package com.example.vue.domain.article;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

}
