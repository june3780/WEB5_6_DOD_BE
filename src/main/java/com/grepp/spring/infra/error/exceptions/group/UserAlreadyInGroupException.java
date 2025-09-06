package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserAlreadyInGroupException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public UserAlreadyInGroupException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
