package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class GroupAuthenticationException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public GroupAuthenticationException(GroupAndMemberErrorCode code) {
        this.code = code;
    }
}
