package com.grepp.spring.infra.error.exceptions.member;

import com.grepp.spring.infra.response.ResponseCode;
import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {
    private final ResponseCode responseCode;

    public InvalidTokenException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

}