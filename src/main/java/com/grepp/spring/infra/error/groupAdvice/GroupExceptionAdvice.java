package com.grepp.spring.infra.error.groupAdvice;


import com.grepp.spring.infra.error.exceptions.group.GroupAuthenticationException;
import com.grepp.spring.infra.error.exceptions.group.GroupNotFoundException;
import com.grepp.spring.infra.error.exceptions.group.NotGroupLeaderException;
import com.grepp.spring.infra.error.exceptions.group.NotGroupUserException;
import com.grepp.spring.infra.error.exceptions.group.NotScheduleLeaderException;
import com.grepp.spring.infra.error.exceptions.group.NotScheduleUserException;
import com.grepp.spring.infra.error.exceptions.group.OnlyOneGroupLeaderException;
import com.grepp.spring.infra.error.exceptions.group.ScheduleAlreadyInGroupException;
import com.grepp.spring.infra.error.exceptions.group.ScheduleNotFoundException;
import com.grepp.spring.infra.error.exceptions.group.UserAlreadyInGroupException;
import com.grepp.spring.infra.error.exceptions.group.UserGroupLeaderException;
import com.grepp.spring.infra.error.exceptions.group.UserNotFoundException;
import com.grepp.spring.infra.response.ApiResponse;
import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import com.grepp.spring.infra.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.grepp.spring.app.controller.api.group")
@Slf4j
@Order(1)
public class GroupExceptionAdvice {

    //401
    @ExceptionHandler(GroupAuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> groupAuthenticationExHandler(
        GroupAuthenticationException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ApiResponse.error(GroupAndMemberErrorCode.AUTHENTICATION_REQUIRED));
    }


    //403
    @ExceptionHandler(NotGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> notGroupLeaderExHandler(
        NotGroupLeaderException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(GroupAndMemberErrorCode.NOT_GROUP_LEADER));
    }

    @ExceptionHandler(NotGroupUserException.class)
    public ResponseEntity<ApiResponse<String>> notGroupUserExHandler(
        NotGroupUserException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(GroupAndMemberErrorCode.NOT_GROUP_MEMBER));
    }

    @ExceptionHandler(NotScheduleLeaderException.class)
    public ResponseEntity<ApiResponse<String>> notScheduleLeaderExHandler(
        NotScheduleLeaderException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(GroupAndMemberErrorCode.NOT_SCHEDULE_LEADER));
    }

    @ExceptionHandler(NotScheduleUserException.class)
    public ResponseEntity<ApiResponse<String>> notScheduleUserExHandler(
        NotScheduleUserException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ApiResponse.error(GroupAndMemberErrorCode.NOT_SCHEDULE_MEMBER));
    }


    // 404
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> groupNotFoundExHandler(
        GroupNotFoundException ex) {
        log.error("404예외");

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.GROUP_NOT_FOUND));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> userNotFoundExHandler(
        UserNotFoundException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.USER_NOT_FOUND));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> scheduleNotFoundExHandler(
        ScheduleNotFoundException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.SCHEDULE_NOT_FOUND));
    }


    // 409
    @ExceptionHandler(UserAlreadyInGroupException.class)
    public ResponseEntity<ApiResponse<String>> userAlreadyExHandler(
        UserAlreadyInGroupException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.USER_ALREADY_IN_GROUP));
    }

    @ExceptionHandler(ScheduleAlreadyInGroupException.class)
    public ResponseEntity<ApiResponse<String>> scheduleAlreadyExHandler(
        ScheduleAlreadyInGroupException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.SCHEDULE_ALREADY_IN_GROUP));
    }

    @ExceptionHandler(OnlyOneGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> onlyOneGroupLeaderExHandler(
        OnlyOneGroupLeaderException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.ONE_GROUP_LEADER));
    }

    @ExceptionHandler(UserGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> userIsGroupLeaderExHandler(
        UserGroupLeaderException ex) {

        return ResponseEntity.status(ResponseCode.NOT_FOUND.status())
            .body(ApiResponse.error(GroupAndMemberErrorCode.USER_GROUP_LEADER));
    }
}
