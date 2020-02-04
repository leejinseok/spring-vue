package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ArticleResponseDto> getArticles(Pageable pageable, @AuthenticationPrincipal User user) {
        return articleService.findAll(pageable, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{articleId}")
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(@PathVariable Long articleId, @AuthenticationPrincipal User user) {
        return articleService.findById(articleId, user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Transactional
    public ArticleResponseDto postArticles(@RequestBody @Valid ArticleRequestDto articleRequestDto, @AuthenticationPrincipal User user) {
        return articleService.save(articleRequestDto, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{articleId}")
    @Transactional
    public void deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal User user) {
        articleService.delete(articleId, user);
    }
}
