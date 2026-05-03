package com.yungwa.domain.lovetype.controller;

import com.yungwa.domain.lovetype.dto.request.LoveTestResultRequest;
import com.yungwa.domain.lovetype.dto.response.LoveTestResultResponse;
import com.yungwa.domain.lovetype.service.LoveTestService;
import com.yungwa.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Love Test", description = "조선 연애 유형 테스트 API")
@RestController
@RequestMapping("/api/love-test")
@RequiredArgsConstructor
public class LoveTestController {

    private final LoveTestService loveTestService;

    @Operation(summary = "테스트 결과 저장", description = "typeCode를 기반으로 유저의 연애 유형을 저장합니다.")
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "연애 유형 저장 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자 또는 유형 코드 없음")
    })
    @PostMapping("/result")
    public ResponseEntity<ApiResponse<LoveTestResultResponse>> saveResult(
            @Valid @RequestBody LoveTestResultRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("연애 유형이 저장되었습니다.", loveTestService.saveResult(request)));
    }
}
