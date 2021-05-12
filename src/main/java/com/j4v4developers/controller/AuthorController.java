package com.j4v4developers.controller;

import com.j4v4developers.dto.AuthorDto;
import com.j4v4developers.request.RequestAuthorSearch;
import com.j4v4developers.request.RequestSaveOrUpdateAuthor;
import com.j4v4developers.service.AuthorService;
import com.j4v4developers.util.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/author-operations")
@Api(value = "Author Rest Service")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/author")
    @ApiOperation(value = "Save or update author author", response = AuthorDto.class)
    public ResponseEntity<BaseResponse<AuthorDto>> saveOrUpdateAuthor(
            @ApiParam(name = "Author save or update request", value = "Athor save or update", required = true)
            @RequestBody RequestSaveOrUpdateAuthor request) {
        AuthorDto authorDto = authorService.saveOrUpdateAuthor(request.getAuthorDto());
        return ResponseEntity.ok(new BaseResponse<>(authorDto, null, null, true));
    }

    @GetMapping(value = "/author/{id}")
    @ApiOperation(value = "Get author by id", response = AuthorDto.class)
    public ResponseEntity<BaseResponse<AuthorDto>> getAuthorById(@PathVariable("id") Long id) {
        AuthorDto authorDto = authorService.getAuthorById(id);
        return ResponseEntity.ok(new BaseResponse<>(authorDto, null, null, true));
    }

    @PutMapping(value = "author/{id}")
    @ApiOperation(value = "Delete author by id", response = AuthorDto.class)
    public ResponseEntity<BaseResponse<AuthorDto>> deleteAuthorById(@PathVariable("id") Long id) {
        AuthorDto authorDto = authorService.deleteAuthorById(id);
        return ResponseEntity.ok(new BaseResponse<>(authorDto,null,null,true));
    }

    @GetMapping(value = "/authors")
    @ApiOperation(value = "Provides all authors by specifications", response = AuthorDto.class)
    public ResponseEntity<BaseResponse<Page<AuthorDto>>> getAuthors(
            @ApiParam(name = "Get All Authors Request", value = "Request for get all authors", required = true)
            @ModelAttribute RequestAuthorSearch authorSearch, Pageable pageable) {
        Page<AuthorDto> dtoList = authorService.getAuthorList(authorSearch, pageable);
        return ResponseEntity.ok(new BaseResponse<>(dtoList, null, null, true));
    }


}
