package com.j4v4developers.request;

import com.j4v4developers.dto.ArticleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "Article save or update request", description = "It includes saved Article")
public class RequestSaveOrUpdateArticle extends BaseRequest {
    @ApiModelProperty(value = "ArticleDto", name = "ArticleDto to save", dataType = "ArticleDto")
    private ArticleDto articleDto;
}
