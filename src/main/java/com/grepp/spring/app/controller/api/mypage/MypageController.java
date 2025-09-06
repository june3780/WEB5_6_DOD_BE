package com.grepp.spring.app.controller.api.mypage;


import com.grepp.spring.app.controller.api.mypage.payload.request.CreateFavoritePlaceRequest;
import com.grepp.spring.app.controller.api.mypage.payload.request.CreateFavoriteTimeRequest;
import com.grepp.spring.app.controller.api.mypage.payload.request.ModifyFavoritePlaceRequest;
import com.grepp.spring.app.controller.api.mypage.payload.request.PublicCalendarIdRequest;
import com.grepp.spring.app.controller.api.mypage.payload.response.CreateFavoritePlaceResponse;
import com.grepp.spring.app.controller.api.mypage.payload.response.CreateFavoriteTimeResponse;
import com.grepp.spring.app.controller.api.mypage.payload.response.ModifyFavoritePlaceResponse;
import com.grepp.spring.app.controller.api.mypage.payload.response.PublicCalendarIdResponse;
import com.grepp.spring.app.model.mainpage.service.PublicCalendarService;
import com.grepp.spring.app.model.mypage.dto.FavoriteLocationDto;
import com.grepp.spring.app.model.mypage.service.FavoriteLocationCommandService;
import com.grepp.spring.app.model.mypage.service.FavoriteLocationQueryService;
import com.grepp.spring.app.model.mypage.service.FavoriteTimetableCommandService;
import com.grepp.spring.app.model.mypage.service.FavoriteTimetableQueryService;
import com.grepp.spring.app.model.mypage.service.PublicCalendarIdService;
import com.grepp.spring.infra.auth.CurrentUser;
import com.grepp.spring.infra.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MypageController {

    private final FavoriteLocationCommandService favoriteLocationCommandService;
    private final FavoriteLocationQueryService favoriteLocationQueryService;
    private final FavoriteTimetableCommandService favoriteTimetableCommandService;
    private final FavoriteTimetableQueryService favoriteTimetableQueryService;
    private final PublicCalendarIdService publicCalendarIdService;
    private final PublicCalendarService publicCalendarService;


    // 즐겨찾기 장소 등록
    @PostMapping("/favorite-location")
    @Operation(summary = "즐겨찾기 장소 등록", description = "회원 즐겨찾기 장소 등록")
    public ResponseEntity<ApiResponse<CreateFavoritePlaceResponse>> createFavoriteLocation(
        @RequestBody @Valid CreateFavoritePlaceRequest request,
        @CurrentUser String userId) {

        // 서비스에서 DTO 받아옴
        FavoriteLocationDto dto = favoriteLocationCommandService.createFavoriteLocation(userId,
            request);

        // 응답용 DTO 로 변환
        CreateFavoritePlaceResponse response = FavoriteLocationDto.fromDto(dto);

        // API 응답 감싸서 반환
        return ResponseEntity.ok(ApiResponse.success(response));

    }

    // 즐겨찾기 시간대 등록 및 수정
    @PostMapping("/favorite-timetable")
    @Operation(summary = "즐겨찾기 시간대 등록 및 수정", description = "회원 즐겨찾기 시간대 등록 및 수정")
    public ResponseEntity<ApiResponse<CreateFavoriteTimeResponse>> createOrUpdateFavoriteTime(
        @RequestBody @Valid CreateFavoriteTimeRequest request,
        @CurrentUser String userId
    ) {

        CreateFavoriteTimeResponse response =
            favoriteTimetableCommandService.createOrUpdateFavoriteTimetable(userId, request);

        // API 응답 감싸서 반환
        return ResponseEntity.ok(ApiResponse.success(response));

    }


    @GetMapping("/favorite-location")
    @Operation(summary = "즐겨찾기 장소 조회", description = "회원 즐겨찾기 장소 조회")
    public ResponseEntity<ApiResponse<List<FavoriteLocationDto>>> getFavoriteLocations(
        @CurrentUser String userId
    ) {

        List<FavoriteLocationDto> response =
            favoriteLocationQueryService.getFavoriteLocations(userId);

        // API 응답 감싸서 반환
        return ResponseEntity.ok(ApiResponse.success(response));

    }

    @GetMapping("/favorite-timetable")
    @Operation(summary = "즐겨찾기 시간대 조회", description = "회원 즐겨찾기 시간대 조회")
    public ResponseEntity<ApiResponse<CreateFavoriteTimeResponse>> getFavoriteTimetables(
        @CurrentUser String userId
    ) {

        CreateFavoriteTimeResponse response =
            favoriteTimetableQueryService.getFavoriteTimetableResponse(userId);

        return ResponseEntity.ok(ApiResponse.success(response));

    }


    // 즐겨찾기 장소 수정
    @Operation(summary = "즐겨찾기 장소 수정", description = "회원 즐겨찾기 장소 수정")
    @PatchMapping("/favorite-location")
    public ResponseEntity<ApiResponse<ModifyFavoritePlaceResponse>> modifyFavoritePlace(
        @RequestBody @Valid ModifyFavoritePlaceRequest request,
        @CurrentUser String userId
    ) {

        FavoriteLocationDto dto =
            favoriteLocationCommandService.modifyFavoriteLocation(userId, request);

        ModifyFavoritePlaceResponse response = FavoriteLocationDto.toModifyResponse(dto);

        return ResponseEntity.ok(ApiResponse.success(response));


    }

    @Operation(summary = "공개 캘린더 ID 조회")
    @GetMapping("/calendar/public-id")
    public ResponseEntity<ApiResponse<PublicCalendarIdResponse>> getPublicCalendarId(
        @CurrentUser String userId
    ) {

        // DB 에서 ID 조회하기
        String publicCalendarId = publicCalendarIdService.getPublicCalendarId(userId)
            .orElse(null); // ID 없으면 null 로 내려주기

        PublicCalendarIdResponse response = new PublicCalendarIdResponse(publicCalendarId);

        return ResponseEntity.ok(ApiResponse.success(response));

    }


    @Operation(summary = "공개 캘린더 ID 입력받기")
    @PostMapping("/calendar/public-id")
    public ResponseEntity<ApiResponse<PublicCalendarIdResponse>> savePublicCalendarId(
        @RequestBody PublicCalendarIdRequest request,
        @CurrentUser String userId
    ) {
        String publicCalendarId = request.getPublicCalendarId();

        // 유효한 캘린더 id 인지 검증 위해 api 호출
        publicCalendarService.fetchPublicCalendarEvents(publicCalendarId);

        // 해당 메서드 위한 서비스를 만듦 -> 합치는 과정을 그 서비스에서 비즈니스 로직 처리 한번에

        // DB 에 저장하기
        publicCalendarIdService.savePublicCalendarId(userId, publicCalendarId);

        PublicCalendarIdResponse response = PublicCalendarIdResponse.builder()
            .calendarId(publicCalendarId)
            .build();

        return ResponseEntity.ok(ApiResponse.success(response));

    }

    @Operation(summary = "공개 캘린더 ID 삭제하기")
    @DeleteMapping("/calendar/public-id")
    public ResponseEntity<ApiResponse<PublicCalendarIdResponse>> deletePublicCalendarId(
        @CurrentUser String userId
    ) {
        publicCalendarIdService.deletePublicCalendarId(userId);
        return ResponseEntity.ok(ApiResponse.success("공개 캘린더 ID가 삭제되었습니다."));
    }


}
