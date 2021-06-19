package com.j4v4developers.aspect;

import com.j4v4developers.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ArticleServiceAspect {


    @After("execution(* com.j4v4developers.service.ArticleService.deleteArticleById(..))")
    public void afterArticleDeleted(JoinPoint joinPoint) {
        log.info("Article has been deleted successfully who has id = " + joinPoint.getArgs()[0]);
    }

    @After("execution(* com.j4v4developers.service.ArticleService.saveOrUpdateArticle(..))")
    public void afterArticleSaveOrUpdate(JoinPoint joinPoint) {
        ArticleDto articleDto = (ArticleDto) joinPoint.getArgs()[0];
        log.info("Article has been inserted successfully who has id = " + articleDto.getArticleId());
    }

}
