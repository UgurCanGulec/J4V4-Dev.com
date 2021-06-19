package com.j4v4developers.dao;

import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.entity.Article;
import com.j4v4developers.entity.Author;
import com.j4v4developers.exception.ArticleNotFoundException;
import com.j4v4developers.exception.AuthorNotFoundException;
import com.j4v4developers.mail.event.ArticleCreatedEvent;
import com.j4v4developers.mapper.ArticleMapper;
import com.j4v4developers.repository.ArticleRepository;
import com.j4v4developers.repository.AuthorRepository;
import com.j4v4developers.request.RequestArticleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ArticleDao(ArticleRepository articleRepository, ArticleMapper articleMapper, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.getArticleByArticleIdAndDeleteFlag(id, false);
        if (article == null) {
            throw new ArticleNotFoundException();
        }else {
            return articleMapper.fromArticleToArticleDto(article, article.getAuthor());
        }
    }

    public ArticleDto saveOrUpdateArticle(ArticleDto articleDto) {
        Author author = authorRepository.getAuthorByAuthorIdAndDeleteFlag(articleDto.getAuthorId(), false);
        if (author == null) {
            throw new AuthorNotFoundException();
        }
        Article article = articleMapper.fromArticleDtoToArticle(articleDto, author);
        article.setAuthor(author);
        article = articleRepository.save(article);
        articleDto = articleMapper.fromArticleToArticleDto(article,author);
        this.publishArticleCreatedEvent(articleDto);
        return articleDto;
    }

    private void publishArticleCreatedEvent(ArticleDto articleDto) {
        applicationEventPublisher.publishEvent(new ArticleCreatedEvent(articleDto));
    }

    public ArticleDto deleteArticleById(Long id) {
        Article article = articleRepository.getArticleByArticleIdAndDeleteFlag(id, false);
        if (article == null) {
            throw new ArticleNotFoundException();
        }else {
            article.setDeleteFlag(true);
            article = articleRepository.save(article);
            return articleMapper.fromArticleToArticleDto(article, article.getAuthor());
        }
    }

    public Page<ArticleDto> findByCriteria(RequestArticleSearch request, Pageable pageable) {
        ArticleRepository.ArticleQuerySpecification querySpecification = new ArticleRepository.ArticleQuerySpecification();
        querySpecification.setArticleTitle(request.getArticleTitle());
        querySpecification.setArticleContentType(request.getArticleContentType());
        querySpecification.setAuthorId(request.getAuthorId());
        querySpecification.setAuthorName(request.getAuthorName());
        querySpecification.setAuthorSurname(request.getAuthorSurname());
        Page<Article> result = articleRepository.findAll(querySpecification, pageable);
        return result.map(article -> articleMapper.fromArticleToArticleDto(article, article.getAuthor()));
    }

}
