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

    public ArticleResponseDto save(ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.save(new Article(articleRequestDto));
        return new ArticleResponseDto(article);
    }

    public List<ArticleResponseDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable)
            .stream()
            .map(ArticleResponseDto::new)
            .collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id, User user) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleException.passNoExistException(id));
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);
        articleResponseDto.setOwn(article.getUser().getId().equals(user.getId()));
        return articleResponseDto;
    }
}
