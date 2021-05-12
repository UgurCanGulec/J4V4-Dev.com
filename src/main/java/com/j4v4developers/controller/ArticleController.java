package com.j4v4developers.controller;

import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.request.RequestArticleSearch;
import com.j4v4developers.request.RequestSaveOrUpdateArticle;
import com.j4v4developers.service.ArticleService;
import com.j4v4developers.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article-operations")
@Api(value = "Article Rest Service")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/article/{id}")
    @ApiOperation(value = "Get article by id")
    public ResponseEntity<BaseResponse<ArticleDto>> getArticleById(
            @PathVariable("id") Long id) {
        ArticleDto articleDto = articleService.getArticleById(id);
        return ResponseEntity.ok(new BaseResponse<>(articleDto,null,null,true));
    }

    @PostMapping(value = "/article")
    @ApiOperation(value = "Save or update article")
    public ResponseEntity<BaseResponse<ArticleDto>> saveOrUpdateAuthor(
            @ApiParam(name = "Article save or update request", value = "Article save or update", required = true)
            @RequestBody RequestSaveOrUpdateArticle request) {
        ArticleDto articleDto = articleService.saveOrUpdateArticle(request.getArticleDto());
        return ResponseEntity.ok(new BaseResponse<>(articleDto, null, null, true));
    }

    @PutMapping(value = "/article/{id}")
    @ApiOperation(value = "Delete article by id")
    public ResponseEntity<BaseResponse<ArticleDto>> deleteArticleById(
            @PathVariable("id") Long id) {
        ArticleDto articleDto = articleService.deleteArticleById(id);
        return ResponseEntity.ok(new BaseResponse<>(articleDto, null,null,true));
    }

    @GetMapping(value = "/articles")
    @ApiOperation(value = "Provides all articles by specifications", response = ArticleDto.class)
    public ResponseEntity<BaseResponse<Page<ArticleDto>>> getArticles(
            @ApiParam(name = "Get All Articles Request", value = "Request for get all articles", required = true)
            @ModelAttribute RequestArticleSearch articleSearch, Pageable pageable) {
        Page<ArticleDto> dtoList = articleService.getArticleList(articleSearch, pageable);
        return ResponseEntity.ok(new BaseResponse<>(dtoList, null, null, true));
    }




}
