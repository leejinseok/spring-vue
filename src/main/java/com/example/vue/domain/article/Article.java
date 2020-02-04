package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
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
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private User user;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Article(ArticleRequestDto articleRequestDto, User user) {
        this.title = articleRequestDto.getTitle();
        this.content = articleRequestDto.getContent();
        this.user = user;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public boolean compareUser(User user) {
        return compareUser(user.getId());
    }

    public boolean compareUser(Long id) {
        return this.user.getId().equals(id);
    }
}
