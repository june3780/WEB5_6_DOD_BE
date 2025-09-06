package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NotScheduleUserException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public NotScheduleUserException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

    public NotScheduleUserException(GroupAndMemberErrorCode code, Exception e) {
        this.code = code;
        log.error(e.getMessage(), e);
    }
}
