package com.j4v4developers.aspect;

import com.j4v4developers.dto.ArticleDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArticleServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceAspect.class);

    @After("execution(* com.j4v4developers.service.ArticleService.deleteArticleById(..))")
    public void afterArticleDeleted(JoinPoint joinPoint) {
        logger.info("Article has been deleted successfully who has id = " + joinPoint.getArgs()[0]);
    }

    @After("execution(* com.j4v4developers.service.ArticleService.saveOrUpdateArticle(..))")
    public void afterArticleSaveOrUpdate(JoinPoint joinPoint) {
        ArticleDto articleDto = (ArticleDto) joinPoint.getArgs()[0];
        logger.info("Article has been inserted successfully who has id = " + articleDto.getArticleId());
    }

}
