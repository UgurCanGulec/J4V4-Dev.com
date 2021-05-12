package com.j4v4developers.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;
    private String errorCode;
    private Map<String, Object> errorDetail;
    private Boolean isSuccess;
}
