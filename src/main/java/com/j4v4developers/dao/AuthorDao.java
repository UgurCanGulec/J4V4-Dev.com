package com.j4v4developers.dao;

import com.j4v4developers.dto.AuthorDto;
import com.j4v4developers.entity.Author;
import com.j4v4developers.exception.AuthorNotFoundException;
import com.j4v4developers.mapper.AuthorMapper;
import com.j4v4developers.repository.AuthorRepository;
import com.j4v4developers.request.RequestAuthorSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorDao {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorDao(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.getAuthorByAuthorIdAndDeleteFlag(id, false);
        if (author == null) {
            throw new AuthorNotFoundException();
        }else {
            return authorMapper.fromAuthorToAuthorDto(author);
        }
    }

    public AuthorDto saveOrUpdateAuthor(AuthorDto authorDto) {
        Author author = authorRepository.save(authorMapper.fromAuthorDtoToAuthor(authorDto));
        return authorMapper.fromAuthorToAuthorDto(author);
    }

    public AuthorDto deleteAuthorById(Long id) {
        Author author = authorRepository.getAuthorByAuthorIdAndDeleteFlag(id, false);
        if (author == null) {
            throw new AuthorNotFoundException();
        }
        author.setDeleteFlag(true);
        author = authorRepository.save(author);
        return authorMapper.fromAuthorToAuthorDto(author);
    }

    public Page<AuthorDto> findByCriteria(RequestAuthorSearch request, Pageable pageable) {
        AuthorRepository.AuthorQuerySpecification querySpecification = new AuthorRepository.AuthorQuerySpecification();
        querySpecification.setAuthorId(request.getAuthorId());
        querySpecification.setName(request.getName());
        querySpecification.setSurname(request.getSurname());
        querySpecification.setAge(request.getAge());
        querySpecification.setJob(request.getJob());
        Page<Author> result = authorRepository.findAll(querySpecification, pageable);
        return result.map(author -> authorMapper.fromAuthorToAuthorDto(author));
    }

}
