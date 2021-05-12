package com.j4v4developers.request;

import com.j4v4developers.dto.AuthorDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "Author save or update request", description = "It includes saved Author")
public class RequestSaveOrUpdateAuthor extends BaseRequest{
    @ApiModelProperty(value = "AuthorDto", name = "AuthorDto to save", dataType = "AuthorDto")
    private AuthorDto authorDto;
}
