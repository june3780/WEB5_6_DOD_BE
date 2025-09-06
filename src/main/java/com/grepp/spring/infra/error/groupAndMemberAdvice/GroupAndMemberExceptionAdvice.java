package com.grepp.spring.infra.error.groupAndMemberAdvice;

import com.grepp.spring.app.controller.api.auth.payload.response.GroupAdminResponse;
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
import com.grepp.spring.infra.error.exceptions.member.InvalidNameException;
import com.grepp.spring.infra.error.exceptions.member.WithdrawNotAllowedException;
import com.grepp.spring.infra.error.exceptions.mypage.AuthenticationRequiredException;
import com.grepp.spring.infra.error.exceptions.mypage.FavoriteAlreadyExistException;
import com.grepp.spring.infra.error.exceptions.mypage.FavoriteNotFoundException;
import com.grepp.spring.infra.error.exceptions.mypage.FavoriteSaveFailedException;
import com.grepp.spring.infra.error.exceptions.mypage.GoogleCalendarApiFailedException;
import com.grepp.spring.infra.error.exceptions.mypage.InvalidFavoriteRequestException;
import com.grepp.spring.infra.error.exceptions.mypage.InvalidMemberRequestException;
import com.grepp.spring.infra.error.exceptions.mypage.InvalidPublicCalendarIdException;
import com.grepp.spring.infra.error.exceptions.mypage.MemberNotFoundException;
import com.grepp.spring.infra.error.exceptions.mypage.PublicCalendarIdNotFoundException;
import com.grepp.spring.infra.error.exceptions.mypage.ScheduleMemberIdNotFoundException;
import com.grepp.spring.infra.response.ApiResponse;
import com.grepp.spring.infra.response.GroupAndMemberErrorCode;
import com.grepp.spring.infra.response.ResponseCode;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.grepp.spring.app.controller.api")
@Slf4j
@Order(1)
public class GroupAndMemberExceptionAdvice {

    // Group Domain
    //401
    @ExceptionHandler(GroupAuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> groupAuthenticationExHandler(
        GroupAuthenticationException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }


    //403
    @ExceptionHandler(NotGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> notGroupLeaderExHandler(
        NotGroupLeaderException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(NotGroupUserException.class)
    public ResponseEntity<ApiResponse<String>> notGroupUserExHandler(
        NotGroupUserException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(NotScheduleLeaderException.class)
    public ResponseEntity<ApiResponse<String>> notScheduleLeaderExHandler(
        NotScheduleLeaderException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(NotScheduleUserException.class)
    public ResponseEntity<ApiResponse<String>> notScheduleUserExHandler(
        NotScheduleUserException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }


    // 404
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> groupNotFoundExHandler(
        GroupNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> userNotFoundExHandler(
        UserNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> scheduleNotFoundExHandler(
        ScheduleNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }


    // 409
    @ExceptionHandler(UserAlreadyInGroupException.class)
    public ResponseEntity<ApiResponse<String>> userAlreadyExHandler(
        UserAlreadyInGroupException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(ScheduleAlreadyInGroupException.class)
    public ResponseEntity<ApiResponse<String>> scheduleAlreadyExHandler(
        ScheduleAlreadyInGroupException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(OnlyOneGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> onlyOneGroupLeaderExHandler(
        OnlyOneGroupLeaderException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(UserGroupLeaderException.class)
    public ResponseEntity<ApiResponse<String>> userIsGroupLeaderExHandler(
        UserGroupLeaderException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }


    // Member Domain
    @ExceptionHandler(WithdrawNotAllowedException.class)
    public ResponseEntity<ApiResponse<?>> withdrawNotAllowedExHandler(WithdrawNotAllowedException ex) {

        List<GroupAdminResponse> groups = ex.getLeaderGroups().stream()
            .map(group -> new GroupAdminResponse(group.getId(), group.getName()))
            .toList();

        return ResponseEntity.status(ResponseCode.NOT_ALLOWED_WITHDRAW.status())
            .body(ApiResponse.error(ResponseCode.NOT_ALLOWED_WITHDRAW, groups));
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ApiResponse<Void>> invalidNameExHandler(InvalidNameException ex) {
        return ResponseEntity.status(ResponseCode.BAD_REQUEST.status())
            .body(ApiResponse.error(ResponseCode.BAD_REQUEST, ex.getMessage()));
    }

    //@ExceptionHandler(MemberNotFoundException.class)
    //public ResponseEntity<ApiResponse<String>> memberNotFoundExHandler(MemberNotFoundException ex) {
    //    return ResponseEntity.status(GroupAndMemberErrorCode.MEMBER_NOT_FOUND.status())
    //        .body(ApiResponse.error(GroupAndMemberErrorCode.MEMBER_NOT_FOUND, GroupAndMemberErrorCode.MEMBER_NOT_FOUND.message()));
    //}

    // 400
    @ExceptionHandler(InvalidFavoriteRequestException.class)
    public ResponseEntity<ApiResponse<String>> invalidFavoriteRequestExHandler(
        InvalidFavoriteRequestException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(InvalidMemberRequestException.class)
    public ResponseEntity<ApiResponse<String>> invalidMemberRequestExHandler(
        InvalidMemberRequestException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(InvalidPublicCalendarIdException.class)
    public ResponseEntity<ApiResponse<String>> invalidPublicCalendarIdExHandler(
        InvalidPublicCalendarIdException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    // 401
    @ExceptionHandler(AuthenticationRequiredException.class) // 멤버 인증 관련
    public ResponseEntity<ApiResponse<String>> authenticationRequiredExHandler(
        AuthenticationRequiredException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    // 404
    @ExceptionHandler(FavoriteNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> favoriteNotFoundExHandlerExHandler(
        FavoriteNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(MemberNotFoundException.class) // 멤버 관련
    public ResponseEntity<ApiResponse<String>> memberNotFoundExHandlerExHandler(
        MemberNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(ScheduleMemberIdNotFoundException.class) // 스케줄 멤버 관련 (일정 비활성화)
    public ResponseEntity<ApiResponse<String>> scheduleMemberIdNotFoundExHandlerExHandler(
        ScheduleMemberIdNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    @ExceptionHandler(PublicCalendarIdNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> publicCalendarIdNotFoundExHandlerExHandler(
        PublicCalendarIdNotFoundException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    // 409
    @ExceptionHandler(FavoriteAlreadyExistException.class)
    public ResponseEntity<ApiResponse<String>> favoriteAlreadyExistExHandler(
        FavoriteAlreadyExistException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    // 500
    @ExceptionHandler(FavoriteSaveFailedException.class)
    public ResponseEntity<ApiResponse<String>> FavoriteSaveFailedExHandler(
        FavoriteSaveFailedException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }

    // 503
    @ExceptionHandler(GoogleCalendarApiFailedException.class)
    public ResponseEntity<ApiResponse<String>> GoogleCalendarApiExHandler(
        GoogleCalendarApiFailedException ex) {
        return ResponseEntity.status(ex.getCode().status())
            .body(ApiResponse.error(ex.getCode()));
    }
}
