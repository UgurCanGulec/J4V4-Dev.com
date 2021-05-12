package com.j4v4developers.repository;

import com.j4v4developers.entity.Article;
import com.j4v4developers.entity.ArticleContentType;
import com.j4v4developers.entity.Author;
import com.j4v4developers.util.AbstractQuerySpecification;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Article getArticleByArticleIdAndDeleteFlag(Long id, Boolean deleteFlag);

    @Getter
    @Setter
    class ArticleQuerySpecification extends AbstractQuerySpecification<Article> {

        String articleTitle;
        ArticleContentType articleContentType;
        String authorName;
        String authorSurname;
        Long authorId;

        @Override
        public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            Join<Article, Author> joinedRoot =root.join("author");
            if (getArticleTitle() != null) {
                predicates.add(criteriaBuilder.equal(root.get("articleTitle"), articleTitle));
            }
            if (getArticleContentType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("articleContentType"), articleContentType));
            }
            if (getAuthorName() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinedRoot.get("name"), authorName)));
            }
            if (getAuthorSurname() != null) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinedRoot.get("surname"), authorSurname)));
            }
            if (getAuthorId() != null) {

                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinedRoot.get("authorId"), authorId)));
            }
            predicates.add(criteriaBuilder.equal(root.get("deleteFlag"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
