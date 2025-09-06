package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ScheduleNotFoundException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public ScheduleNotFoundException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
