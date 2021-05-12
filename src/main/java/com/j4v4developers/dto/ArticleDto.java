package com.j4v4developers.dto;

import com.j4v4developers.entity.ArticleContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {

    private Long articleId;
    private String articleContent;
    private ArticleContentType articleContentType;
    private String articleTitle;
    private Long authorId;
    private Boolean deleteFlag;
}
