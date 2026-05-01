package com.yungwa.domain.auth.dto.request;

import com.yungwa.domain.user.domain.Gender;
import com.yungwa.domain.user.domain.LoveType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @Schema(description = "사용자의 인스타그램 ID", example = "test_123")
        @NotBlank String instagramId,

        @Schema(description = "로그인에 사용할 비밀번호", example = "1234")
        @NotBlank @Size(min = 4) String password,

        @Schema(description = "성별", example = "FEMALE")
        @NotNull Gender gender,

        @Schema(description = "MBTI", example = "ENFP")
        String mbti,

        @Schema(description = "조선시대 연애 유형", example = "JANGGUN")
        LoveType loveType,

        @Schema(description = "카드에 표시될 한 줄 자기소개", example = "활 잘 쏘는 사람 좋아합니다")
        @Size(max = 255) String introduction,

        @Schema(description = "카드 대표 이모지", example = "🏹")
        String emoji
) {
}
