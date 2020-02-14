package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponseDto save(ArticleRequestDto articleRequestDto, User user) {

        Article article = articleRepository.save(new Article(articleRequestDto, user));

        return new ArticleResponseDto(article, user);
    }

    public ArticleResponseDto update(Long articleId, ArticleRequestDto articleRequestDto, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(ArticleException.passNoExistException(articleId));
        if (!article.doesUserHasThis(user)) {
            throw new ArticleException.AccessNotOwned(articleId);
        }

        article.update(articleRequestDto);

        return new ArticleResponseDto(articleRepository.save(article), user);
    }

    public Page<ArticleResponseDto> findAll(Pageable pageable, User user) {
        List<ArticleResponseDto> contents = articleRepository.findAll(pageable)
            .stream()
            .map(article -> new ArticleResponseDto(article, user))
            .collect(Collectors.toList());

        int total = articleRepository.findTotal();

        Page<ArticleResponseDto> page = new PageImpl<>(contents, pageable, total);

        return page;
    }

    public ArticleResponseDto findById(Long id, User user) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleException.passNoExistException(id));
        return new ArticleResponseDto(article, user);
    }

    public void delete(Long id, User user) {
        articleRepository.deleteById(id, user);
    }

    private void doesUserHasThisArticle(Long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(ArticleException.passNoExistException(articleId));
        if (!article.compareUser(user)) {
            throw new ArticleException.AccessNotOwned(articleId);
        }
    }
}
