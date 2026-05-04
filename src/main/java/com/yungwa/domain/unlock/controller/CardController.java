package com.yungwa.domain.unlock.controller;

import com.yungwa.domain.unlock.dto.response.CardProfileResponse;
import com.yungwa.domain.unlock.dto.response.UnlockedUserResponse;
import com.yungwa.domain.unlock.dto.response.UnlockResponse;
import com.yungwa.domain.unlock.service.UnlockService;
import com.yungwa.global.response.ApiResponse;
import com.yungwa.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Card", description = "카드 조회 및 잠금 해제 API")
@RestController
@RequiredArgsConstructor
public class CardController {

    private final UnlockService unlockService;

    @Operation(
            summary = "카드 잠금 해제",
            description = "티켓 1장을 사용해 대상 사용자의 인스타그램 ID를 열람합니다. 이미 열람한 경우 무료로 반환합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "열람 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "자기 자신 열람 불가"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "402", description = "티켓 부족"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자 없음")
    })
    @PostMapping("/api/cards/{targetUserId}/unlock")
    public ResponseEntity<ApiResponse<UnlockResponse>> unlock(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long targetUserId) {
        return ResponseEntity.ok(
                ApiResponse.success("열람이 완료되었습니다.",
                        unlockService.unlock(userDetails.getUserId(), targetUserId)));
    }

    @Operation(
            summary = "열람한 카드 목록 조회",
            description = "내가 이미 잠금 해제한 사용자 목록을 반환합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/api/unlocks/me")
    public ResponseEntity<ApiResponse<List<UnlockedUserResponse>>> getUnlockedList(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                ApiResponse.success("열람 목록을 조회했습니다.",
                        unlockService.getUnlockedList(userDetails.getUserId())));
    }

    @Operation(
            summary = "카드 공개 프로필 조회",
            description = "특정 사용자의 공개 프로필을 조회합니다. 인스타그램 ID는 잠금 해제 후 별도 확인이 필요합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/api/cards/{userId}")
    public ResponseEntity<ApiResponse<CardProfileResponse>> getCardProfile(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success("카드 프로필을 조회했습니다.",
                        unlockService.getCardProfile(userId)));
    }
}
