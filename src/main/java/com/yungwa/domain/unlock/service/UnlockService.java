package com.yungwa.domain.unlock.service;

import com.yungwa.domain.ticket.entity.TicketTransaction;
import com.yungwa.domain.ticket.repository.TicketTransactionRepository;
import com.yungwa.domain.unlock.dto.response.CardProfileResponse;
import com.yungwa.domain.unlock.dto.response.UnlockedUserResponse;
import com.yungwa.domain.unlock.dto.response.UnlockResponse;
import com.yungwa.domain.unlock.entity.AccessLog;
import com.yungwa.domain.unlock.repository.AccessLogRepository;
import com.yungwa.domain.user.entity.User;
import com.yungwa.domain.user.repository.UserRepository;
import com.yungwa.global.exception.CustomException;
import com.yungwa.global.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UnlockService {

    private final UserRepository userRepository;
    private final AccessLogRepository accessLogRepository;
    private final TicketTransactionRepository ticketTransactionRepository;

    @Transactional
    public UnlockResponse unlock(Long viewerId, Long targetUserId) {
        if (viewerId.equals(targetUserId)) {
            throw new CustomException(ErrorCode.CANNOT_UNLOCK_SELF);
        }

        User viewer = userRepository.findById(viewerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User target = userRepository.findById(targetUserId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (accessLogRepository.existsByViewerAndTarget(viewer, target)) {
            return new UnlockResponse(target.getInstagramId());
        }

        if (viewer.getTicketCount() < 1) {
            throw new CustomException(ErrorCode.INSUFFICIENT_TICKETS);
        }

        viewer.updateTicketCount(-1);

        ticketTransactionRepository.save(TicketTransaction.builder()
                .user(viewer)
                .delta(-1)
                .reason("CARD_UNLOCK")
                .build());

        accessLogRepository.save(AccessLog.builder()
                .viewer(viewer)
                .target(target)
                .build());

        return new UnlockResponse(target.getInstagramId());
    }

    @Transactional(readOnly = true)
    public List<UnlockedUserResponse> getUnlockedList(Long viewerId) {
        User viewer = userRepository.findById(viewerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return accessLogRepository.findByViewerWithTargetOrderByAccessedAtDesc(viewer)
                .stream()
                .map(log -> UnlockedUserResponse.from(log.getTarget()))
                .toList();
    }

    @Transactional(readOnly = true)
    public CardProfileResponse getCardProfile(Long userId) {
        User user = userRepository.findByIdWithLoveType(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return CardProfileResponse.from(user);
    }
}
