package com.yungwa.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public record MyProfileUpdateRequest(

        @Schema(description = "MBTI (null 전달 시 변경하지 않음)", example = "ENFP")
        String mbti,

        @Schema(description = "한 줄 자기소개 (null 전달 시 변경하지 않음)", example = "활 잘 쏘는 사람 좋아합니다")
        @Size(max = 255) String introduction,

        @Schema(description = "대표 이모지 (null 전달 시 변경하지 않음)", example = "🏹")
        String emoji
) {
}
