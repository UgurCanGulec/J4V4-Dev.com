package com.j4v4developers.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "author")
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorId;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_surname")
    private String surname;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Article> articles;
    @Column(name = "author_job")
    private String job;
    @Column(name = "author_age")
    private int age;
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

}
