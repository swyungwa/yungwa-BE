package com.yungwa.domain.unlock.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UnlockResponse(

        @Schema(description = "열람된 사용자의 인스타그램 ID")
        String instagramId
) {
}
