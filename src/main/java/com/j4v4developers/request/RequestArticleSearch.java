package com.j4v4developers.request;

import com.j4v4developers.entity.ArticleContentType;
import lombok.Data;

@Data
public class RequestArticleSearch {

    private String articleTitle;
    private ArticleContentType articleContentType;
    private String authorName;
    private String authorSurname;
    private Long authorId;
}
