package com.grepp.spring.infra.error.exceptions.mypage;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class InvalidMemberRequestException extends RuntimeException {

  private final GroupAndMemberErrorCode code;

  public InvalidMemberRequestException(GroupAndMemberErrorCode code) {
    this.code = code;
  }

  public InvalidMemberRequestException(GroupAndMemberErrorCode code, Exception e) {
    this.code = code;
    log.error(e.getMessage(),e);
  }


}
