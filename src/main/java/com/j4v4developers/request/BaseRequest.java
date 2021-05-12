package com.j4v4developers.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseRequest {

    private String language;
    private String applicationId;
    private Long transactionId;

}
