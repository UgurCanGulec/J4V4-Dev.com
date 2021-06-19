package com.j4v4developers.service;

import com.j4v4developers.dao.ArticleDao;
import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.exception.ArticleNotFoundException;
import com.j4v4developers.request.RequestArticleSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ArticleService {

    private final ArticleDao articleDao;


    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional
    public ArticleDto saveOrUpdateArticle(ArticleDto articleDto) {
        return articleDao.saveOrUpdateArticle(articleDto);
    }

    public ArticleDto getArticleById(Long id) throws ArticleNotFoundException {
        return articleDao.getArticleById(id);
    }

    @Transactional
    public ArticleDto deleteArticleById(Long id) {
        return articleDao.deleteArticleById(id);
    }


    public Page<ArticleDto> getArticleList(RequestArticleSearch request, Pageable pageable) {
        Page<ArticleDto> result = articleDao.findByCriteria(request, pageable);
        if (!result.hasContent()) {
            throw new ArticleNotFoundException();
        }
        return result;
    }

}
