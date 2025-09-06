package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class UserGroupLeaderException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public UserGroupLeaderException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
