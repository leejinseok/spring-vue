package com.example.vue.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleResponseDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable)
            .stream()
            .map(ArticleResponseDto::new)
            .collect(Collectors.toList());
    }
}
