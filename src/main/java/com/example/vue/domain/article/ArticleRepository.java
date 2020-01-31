package com.example.vue.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public List<Article> findAll(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        return em.createNamedQuery("findAll", Article.class)
            .setFirstResult(page * size)
            .setMaxResults(page * size + size)
            .getResultList();
    }

}
