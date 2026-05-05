package com.yungwa.domain.lovetype.dto.request;

import com.yungwa.domain.lovetype.entity.LoveTypeCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoveTestResultRequest(

        @Schema(description = "사용자 ID", example = "1")
        @NotNull Long userId,

        @Schema(description = "테스트 결과 유형 코드", example = "GENERAL",
                allowableValues = {"SATTO", "GENERAL", "YANGBAN", "DOLSOE", "ROYAL", "CLOWN"})
        @NotNull LoveTypeCode typeCode
) {
}
