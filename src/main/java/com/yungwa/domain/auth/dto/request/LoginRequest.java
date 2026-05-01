package com.yungwa.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Schema(description = "사용자의 인스타그램 ID", example = "test_123")
        @NotBlank String instagramId,

        @Schema(description = "로그인에 사용할 비밀번호", example = "1234")
        @NotBlank @Size(min = 4) String password
) {
}
