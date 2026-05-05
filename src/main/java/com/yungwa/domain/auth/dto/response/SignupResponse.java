package com.yungwa.domain.auth.dto.response;

import com.yungwa.domain.lovetype.entity.LoveTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignupResponse(
        @Schema(description = "생성된 사용자 ID", example = "1")
        Long userId,

        @Schema(description = "사용자의 인스타그램 ID", example = "test_123")
        String instagramId,

        @Schema(description = "성별", example = "FEMALE")
        String gender,

        @Schema(description = "MBTI", example = "ENFP")
        String mbti,

        @Schema(description = "카드 한 줄 자기소개", example = "활 잘 쏘는 사람 좋아합니다")
        String introduction,

        @Schema(description = "카드 대표 이모지", example = "🏹")
        String emoji,

        @Schema(description = "연애 유형 코드", example = "GENERAL")
        LoveTypeCode loveTypeCode,

        @Schema(description = "연애 유형 이름 (한글)", example = "장군")
        String loveTypeName,

        @Schema(description = "JWT 액세스 토큰")
        String token
) {
}
