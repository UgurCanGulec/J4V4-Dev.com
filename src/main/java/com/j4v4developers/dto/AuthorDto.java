package com.j4v4developers.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthorDto implements Serializable {

    private Long authorId;
    private String name;
    private String surname;
    private List<ArticleDto> articles;
    private String job;
    private int age;
    private Boolean deleteFlag;
}
