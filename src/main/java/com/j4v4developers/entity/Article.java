package com.j4v4developers.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "article")
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    @Column(name = "article_content", nullable = false, length = 500)
    private String articleContent;

    @Enumerated
    @Column(name = "article_content-type")
    private ArticleContentType articleContentType;

    @Column(name = "article_title")
    private String articleTitle;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "authorId")
    private Author author;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;


}
