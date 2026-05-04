package com.yungwa.domain.user.controller;

import com.yungwa.domain.user.dto.request.MyProfileUpdateRequest;
import com.yungwa.domain.user.dto.response.MyProfileResponse;
import com.yungwa.domain.user.service.UserService;
import com.yungwa.global.response.ApiResponse;
import com.yungwa.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "사용자 프로필 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "내 프로필 조회",
            description = "티켓 수, 연애 유형을 포함한 내 전체 프로필을 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MyProfileResponse>> getMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                ApiResponse.success("프로필을 조회했습니다.",
                        userService.getMyProfile(userDetails.getUserId())));
    }

    @Operation(
            summary = "내 프로필 수정",
            description = "MBTI, 자기소개, 이모지를 부분 수정합니다. null 필드는 변경되지 않습니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<MyProfileResponse>> updateMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody MyProfileUpdateRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("프로필이 수정되었습니다.",
                        userService.updateMyProfile(userDetails.getUserId(), request)));
    }
}
