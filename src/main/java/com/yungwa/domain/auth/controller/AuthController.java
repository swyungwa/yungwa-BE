package com.yungwa.domain.auth.controller;

import com.yungwa.domain.auth.dto.request.LoginRequest;
import com.yungwa.domain.auth.dto.request.SignupRequest;
import com.yungwa.domain.auth.dto.response.LoginResponse;
import com.yungwa.domain.auth.dto.response.SignupResponse;
import com.yungwa.domain.auth.service.AuthService;
import com.yungwa.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody @Valid SignupRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("카드 등록이 완료되었습니다.", authService.signup(signupRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success("로그인에 성공했습니다.", authService.login(loginRequest)));
    }
}
