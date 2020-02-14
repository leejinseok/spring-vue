package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    public List<Article> findAll(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        return em.createNamedQuery("findAll", Article.class)
            .setFirstResult(page * size)
            .setMaxResults(page * size + size)
            .getResultList();
    }

    public Integer findTotal() {
        return em.createNamedQuery("findAll", Article.class)
            .setFirstResult(0)
            .setMaxResults(999999999)
            .getResultList().size();
    }

    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }

    public void deleteById(Long id, User user) {
        Article article = findById(id).orElseThrow(ArticleException.passNoExistException(id));
        if (!article.compareUser(user)) {
            throw new ArticleException.AccessNotOwned(id);
        }
        em.remove(article);
    }

}
