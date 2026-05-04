package com.yungwa.domain.ticket.dto.response;

import com.yungwa.domain.user.entity.User;

public record TicketGrantResponse(
        Long userId,
        String instagramId,
        int ticketCount
) {
    public static TicketGrantResponse from(User user) {
        return new TicketGrantResponse(user.getId(), user.getInstagramId(), user.getTicketCount());
    }
}
