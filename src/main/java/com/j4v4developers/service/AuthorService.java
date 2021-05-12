package com.j4v4developers.service;

import com.j4v4developers.dao.AuthorDao;
import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.dto.AuthorDto;
import com.j4v4developers.entity.Author;
import com.j4v4developers.exception.AuthorNotFoundException;
import com.j4v4developers.exception.RecordCouldNotDeleteException;
import com.j4v4developers.request.RequestAuthorSearch;
import com.j4v4developers.validation.AuthorValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Transactional
    public AuthorDto saveOrUpdateAuthor(AuthorDto authorDto) {
        return authorDao.saveOrUpdateAuthor(authorDto);
    }

    public AuthorDto getAuthorById(Long id) throws AuthorNotFoundException {
        AuthorDto authorDto = authorDao.getAuthorById(id);
        List<ArticleDto> articles = authorDto.getArticles().stream()
                .filter(item -> Boolean.FALSE.equals(item.getDeleteFlag()))
                .collect(Collectors.toList());
        authorDto.setArticles(articles);
        return authorDto;
    }

    @Transactional
    public AuthorDto deleteAuthorById(Long id) throws AuthorNotFoundException {
        AuthorDto authorDto = authorDao.getAuthorById(id);
        if (Boolean.TRUE.equals(AuthorValidations.validateAuthorIsAvailableForDelete.apply(authorDto))){
            return authorDao.deleteAuthorById(id);
        }else {
            throw new RecordCouldNotDeleteException();
        }
    }

    public Page<AuthorDto> getAuthorList(RequestAuthorSearch request, Pageable pageable) {
        Page<AuthorDto> result = authorDao.findByCriteria(request, pageable);
        if (!result.hasContent()) {
            throw new AuthorNotFoundException();
        }
        return result;
    }


}
