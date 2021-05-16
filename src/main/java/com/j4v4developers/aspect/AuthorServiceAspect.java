package com.j4v4developers.aspect;

import com.j4v4developers.dao.AuthorDao;
import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.dto.AuthorDto;
import com.j4v4developers.exception.AuthorNotFoundException;
import com.j4v4developers.exception.RecordCouldNotDeleteException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Aspect
@Component
public class AuthorServiceAspect {

    private final AuthorDao authorDao;
    private final Logger logger = LoggerFactory.getLogger(AuthorServiceAspect.class);

    @Autowired
    public AuthorServiceAspect(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Before("execution(* com.j4v4developers.service.AuthorService.deleteAuthorById(..))")
   public void validateAuthorIsAvailableForDelete(JoinPoint joinPoint) {
        AuthorDto authorDto = authorDao.getAuthorById(Long.valueOf(joinPoint.getArgs()[0].toString()));
        if (authorDto == null) {
            throw new AuthorNotFoundException();
        }
        List<ArticleDto> articles = authorDto.getArticles()
                .stream()
                .filter((articleDto -> Boolean.FALSE.equals(articleDto.getDeleteFlag())))
                .collect(Collectors.toList());
        if (Boolean.FALSE.equals(articles.isEmpty())) {
            throw new RecordCouldNotDeleteException();
        }

   }

   @After("execution(* com.j4v4developers.service.AuthorService.deleteAuthorById(..))")
   public void afterAuthorIsDeleted(JoinPoint joinPoint) {
        logger.info("Author has been deleted successfully who has id = " + joinPoint.getArgs()[0].toString());
   }

   @After("execution(* com.j4v4developers.service.AuthorService.saveOrUpdateAuthor(..))")
   public void afterAuthorSaveOrUpdate(JoinPoint joinPoint) {
        AuthorDto authorDto = (AuthorDto) joinPoint.getArgs()[0];
        logger.info("Author has been inserted who has name & surname = " + authorDto.getName() + " & " + authorDto.getSurname());
   }


}
