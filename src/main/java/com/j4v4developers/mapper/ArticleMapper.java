package com.j4v4developers.mapper;

import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.entity.Article;
import com.j4v4developers.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(source = "articleDto.deleteFlag", target = "deleteFlag")
    Article fromArticleDtoToArticle(ArticleDto articleDto, Author author);

    @Mapping(source = "article.deleteFlag", target = "deleteFlag")
    ArticleDto fromArticleToArticleDto(Article article, Author author);



}
