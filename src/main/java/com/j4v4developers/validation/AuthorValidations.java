package com.j4v4developers.validation;


import com.j4v4developers.dto.ArticleDto;
import com.j4v4developers.dto.AuthorDto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AuthorValidations {

    private AuthorValidations() {
    }

    public static final Function<AuthorDto, Boolean> validateAuthorIsAvailableForDelete = (authorDto -> {
        List<ArticleDto> articles = authorDto.getArticles()
                .stream()
                .filter(item -> Boolean.FALSE.equals(item.getDeleteFlag()))
                .collect(Collectors.toList());
        return articles.isEmpty();
    });


}
