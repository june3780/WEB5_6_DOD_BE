package com.grepp.spring.infra.error.exceptions.group;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GroupNotFoundException extends RuntimeException {

    private final GroupAndMemberErrorCode code;

    public GroupNotFoundException(GroupAndMemberErrorCode code) {
        this.code = code;
    }

}
