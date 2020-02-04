package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(value = "/{articleId}")
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(@PathVariable Long articleId, @AuthenticationPrincipal User user) {
        return articleService.findById(articleId, user);
    }

    @PostMapping
    @Transactional
    public ArticleResponseDto postArticles(@RequestBody @Valid ArticleRequestDto articleRequestDto) {
        return articleService.save(articleRequestDto);
    }
}
