package com.example.vue.domain.article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NamedQuery(name = "findAll", query = "select a from Article a order by a.createdAt desc")
@NoArgsConstructor
public class Article {

    @Id @GeneratedValue
    Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Article(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.content = articleRequestDto.getContent();
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
