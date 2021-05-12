package com.j4v4developers.repository;

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
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    Author getAuthorByAuthorIdAndDeleteFlag(Long id, Boolean deleteFlag);

    @Getter
    @Setter
    class AuthorQuerySpecification extends AbstractQuerySpecification<Author> {
        Long authorId;
        String name;
        String surname;
        String job;
        Integer age;


        @Override
        public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (getAuthorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("authorId"), authorId));
            }
            if (getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
            if (getSurname() != null) {
                predicates.add(criteriaBuilder.equal(root.get("surname"), surname));
            }
            if (getJob() != null) {
                predicates.add(criteriaBuilder.equal(root.get("job"), job));
            }
            if (getAge() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), age));
            }
            predicates.add(criteriaBuilder.equal(root.get("deleteFlag"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
