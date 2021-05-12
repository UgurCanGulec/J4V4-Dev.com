package com.j4v4developers.request;

import lombok.Data;

@Data
public class RequestAuthorSearch {

    private Long authorId;
    private String name;
    private String surname;
    private String job;
    private Integer age;

}
