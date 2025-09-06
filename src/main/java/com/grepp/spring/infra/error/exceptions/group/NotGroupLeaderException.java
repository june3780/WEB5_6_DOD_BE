package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class NotGroupLeaderException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public NotGroupLeaderException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
