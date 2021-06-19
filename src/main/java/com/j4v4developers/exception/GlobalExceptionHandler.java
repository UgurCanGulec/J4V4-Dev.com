package com.j4v4developers.exception;

import com.j4v4developers.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        errors.forEach(log::error);
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.BAD_REQUEST.name(), body, false), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler({AuthorNotFoundException.class})
    public ResponseEntity<Object> handleAuthorNotFoundException(AuthorNotFoundException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Auhtor Not Found !");
        body.put("error message", ex.getMessage());
        log.error("Author Not Found !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.NOT_FOUND.name(), body,false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AuthorListIsEmptyException.class})
    public ResponseEntity<Object> handleAuthorListIsEmptyException(AuthorNotFoundException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Author List Is Empty !");
        body.put("error message", ex.getMessage());
        log.error("Author List Is Empty !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.IS_EMPTY.name(), body,false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ArticleNotFoundException.class})
    public ResponseEntity<Object> handleArticleNotFoundException(ArticleNotFoundException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Article Not Found !");
        body.put("error message", ex.getMessage());
        log.error("Article Not Found !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.NOT_FOUND.name(), body,false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecordCouldNotDeleteException.class})
    public ResponseEntity<Object> handleRecordCouldNotDeleteException(RecordCouldNotDeleteException ex, WebRequest webRequest) {
        Map<String ,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Record could not delete because of child records !");
        body.put("error message", ex.getMessage());
        log.error("Record could not delete because of child records !");
        return new ResponseEntity<>(new BaseResponse<>(null, ErrorType.BAD_REQUEST.name(), body,false), HttpStatus.BAD_REQUEST);
    }


}
