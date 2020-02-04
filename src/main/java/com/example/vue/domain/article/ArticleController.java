package com.example.vue.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<ArticleResponseDto> getArticles(Pageable pageable) {
        return articleService.findAll(pageable);
    }

    @PostMapping
    @Transactional
    public ArticleResponseDto postArticles(@RequestBody @Valid ArticleRequestDto articleRequestDto) {
        return articleService.save(articleRequestDto);
    }
}
