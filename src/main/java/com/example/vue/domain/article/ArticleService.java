package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponseDto save(ArticleRequestDto articleRequestDto, User user) {
        Article article = articleRepository.save(new Article(articleRequestDto));
        return new ArticleResponseDto(article, user);
    }

    public List<ArticleResponseDto> findAll(Pageable pageable, User user) {
        return articleRepository.findAll(pageable)
            .stream()
            .map(article -> new ArticleResponseDto(article, user))
            .collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id, User user) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleException.passNoExistException(id));
        return new ArticleResponseDto(article, user);
    }

    public void delete(Long id, User user) {
        articleRepository.deleteById(id, user);
    }
}
