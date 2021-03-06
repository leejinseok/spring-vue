package com.example.vue.domain.article;

import com.example.vue.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<ArticleResponseDto> getArticles(Pageable pageable, @AuthenticationPrincipal User user) {
        return articleService.findAll(pageable, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{articleId}")
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(@PathVariable Long articleId, @AuthenticationPrincipal User user) {
        return articleService.findById(articleId, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{articleId}")
    @Transactional
    public ArticleResponseDto updateArticle(@PathVariable Long articleId,
                                            @RequestBody @Valid ArticleRequestDto articleRequestDto,
                                            @AuthenticationPrincipal User user) {

        return articleService.update(articleId, articleRequestDto, user);
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
