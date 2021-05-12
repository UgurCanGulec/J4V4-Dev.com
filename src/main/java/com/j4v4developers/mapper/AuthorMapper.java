package com.j4v4developers.mapper;

import com.j4v4developers.dto.AuthorDto;
import com.j4v4developers.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author fromAuthorDtoToAuthor(AuthorDto authorDto);

    AuthorDto fromAuthorToAuthorDto(Author author);

}
