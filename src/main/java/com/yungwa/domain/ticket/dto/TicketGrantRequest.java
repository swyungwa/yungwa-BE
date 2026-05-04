package com.yungwa.domain.ticket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TicketGrantRequest(

        @Schema(description = "관리자 인증 코드")
        @NotBlank String adminCode,

        @Schema(description = "지급할 티켓 수량", example = "5")
        @Positive int amount
) {
}
