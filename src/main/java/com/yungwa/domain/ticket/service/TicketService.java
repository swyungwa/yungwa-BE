package com.yungwa.domain.ticket.service;

import com.yungwa.domain.ticket.dto.TicketGrantRequest;
import com.yungwa.domain.ticket.dto.response.TicketGrantResponse;
import com.yungwa.domain.ticket.entity.TicketTransaction;
import com.yungwa.domain.ticket.repository.TicketTransactionRepository;
import com.yungwa.domain.user.entity.User;
import com.yungwa.domain.user.repository.UserRepository;
import com.yungwa.global.exception.CustomException;
import com.yungwa.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final UserRepository userRepository;
    private final TicketTransactionRepository ticketTransactionRepository;

    @Value("${admin.secret-code}")
    private String adminSecretCode;

    @Transactional
    public TicketGrantResponse grantTickets(Long userId, TicketGrantRequest request) {
        if (!adminSecretCode.equals(request.adminCode())) {
            throw new CustomException(ErrorCode.INVALID_ADMIN_CODE);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateTicketCount(request.amount());

        ticketTransactionRepository.save(TicketTransaction.builder()
                .user(user)
                .delta(request.amount())
                .reason("ADMIN_DIRECT_GRANT")
                .build());

        return TicketGrantResponse.from(user);
    }
}
