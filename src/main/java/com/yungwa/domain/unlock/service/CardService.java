package com.yungwa.domain.unlock.service;

import com.yungwa.domain.unlock.dto.response.CardListItemResponse;
import com.yungwa.domain.unlock.repository.AccessLogRepository;
import com.yungwa.domain.user.domain.GenderFilter;
import com.yungwa.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final UserRepository userRepository;
    private final AccessLogRepository accessLogRepository;

    @Transactional(readOnly = true)
    public List<CardListItemResponse> getCards(Long viewerId, GenderFilter genderFilter) {
        // 1번 쿼리: 필터링된 유저 목록 (loveType fetch join)
        List<com.yungwa.domain.user.entity.User> users =
                userRepository.findCardsByGenderFilter(viewerId, genderFilter);

        // 2번 쿼리: 내가 열람한 targetId Set
        Set<Long> unlockedTargetIds =
                accessLogRepository.findUnlockedTargetIdsByViewerId(viewerId);

        return users.stream()
                .map(user -> CardListItemResponse.of(user, unlockedTargetIds.contains(user.getId())))
                .toList();
    }
}
