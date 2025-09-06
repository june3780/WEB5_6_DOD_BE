package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class NotScheduleLeaderException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public NotScheduleLeaderException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

    public NotScheduleLeaderException(GroupAndMemberErrorCode code, Exception e) {
        this.code = code;
        log.error(e.getMessage(), e);
    }
}
