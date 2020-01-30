package com.example.vue.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

}
