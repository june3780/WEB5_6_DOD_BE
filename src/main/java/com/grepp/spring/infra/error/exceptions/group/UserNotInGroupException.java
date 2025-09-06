package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotInGroupException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public UserNotInGroupException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
