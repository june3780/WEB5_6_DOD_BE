package com.grepp.spring.infra.error.exceptions.mypage;

import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class FavoriteNotFoundException extends RuntimeException {

  private final GroupAndMemberErrorCode code;

  public FavoriteNotFoundException(GroupAndMemberErrorCode code) {
    this.code = code;
  }

}
