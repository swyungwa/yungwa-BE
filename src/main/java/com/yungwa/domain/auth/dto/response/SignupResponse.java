package com.yungwa.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignupResponse(
        @Schema(description = "생성된 사용자 ID", example = "1")
        Long userId,

        @Schema(description = "사용자의 인스타그램 ID", example = "test_123")
        String instagramId
) {
}
