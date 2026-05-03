package com.yungwa.domain.lovetype.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoveTestResultRequest(

        @Schema(description = "사용자 ID", example = "1")
        @NotNull Long userId,

        @Schema(description = "테스트 결과 유형 코드", example = "JANGGUN")
        @NotBlank String typeCode
) {
}
