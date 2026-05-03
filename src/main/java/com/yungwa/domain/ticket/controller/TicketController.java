package com.yungwa.domain.ticket.controller;

import com.yungwa.domain.ticket.dto.TicketGrantRequest;
import com.yungwa.domain.ticket.dto.response.TicketGrantResponse;
import com.yungwa.domain.ticket.service.TicketService;
import com.yungwa.global.response.ApiResponse;
import com.yungwa.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ticket", description = "티켓 관리 API")
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @Operation(
            summary = "티켓 지급 (현장 관리자)",
            description = "JWT로 인증된 사용자의 기기에서 관리자 코드를 입력해 티켓을 직접 지급합니다.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "티켓 지급 성공"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패 또는 관리자 코드 불일치"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자 없음")
    })
    @PostMapping("/grant")
    public ResponseEntity<ApiResponse<TicketGrantResponse>> grantTickets(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody TicketGrantRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("티켓이 지급되었습니다.",
                        ticketService.grantTickets(userDetails.getUserId(), request)));
    }
}
