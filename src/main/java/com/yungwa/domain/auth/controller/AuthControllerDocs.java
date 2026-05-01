package com.yungwa.domain.auth.controller;

import com.yungwa.domain.auth.dto.request.LoginRequest;
import com.yungwa.domain.auth.dto.request.SignupRequest;
import com.yungwa.domain.auth.dto.response.LoginResponse;
import com.yungwa.domain.auth.dto.response.SignupResponse;
import com.yungwa.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDocs {

    @Operation(summary = "카드 등록 (회원가입)", description = "인스타그램 ID, 비밀번호, 성별 등 카드 정보를 입력하여 계정을 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "카드 등록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "입력값 오류"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "중복된 인스타그램 ID")
    })
    ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody @Valid SignupRequest signupRequest);

    @Operation(summary = "로그인", description = "인스타그램 ID와 비밀번호로 로그인합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "입력값 오류"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "비밀번호 불일치"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "삭제된 계정"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 계정")
    })
    ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest);
}
