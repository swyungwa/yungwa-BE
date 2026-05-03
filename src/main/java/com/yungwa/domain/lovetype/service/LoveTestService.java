package com.yungwa.domain.lovetype.service;

import com.yungwa.domain.lovetype.dto.request.LoveTestResultRequest;
import com.yungwa.domain.lovetype.dto.response.LoveTestResultResponse;
import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.lovetype.repository.LoveTypeRepository;
import com.yungwa.domain.user.entity.User;
import com.yungwa.domain.user.repository.UserRepository;
import com.yungwa.global.exception.CustomException;
import com.yungwa.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoveTestService {

    private final UserRepository userRepository;
    private final LoveTypeRepository loveTypeRepository;

    @Transactional
    public LoveTestResultResponse saveResult(LoveTestResultRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        LoveType loveType = loveTypeRepository.findByTypeCode(request.typeCode())
                .orElseThrow(() -> new CustomException(ErrorCode.LOVE_TYPE_NOT_FOUND));

        user.updateLoveType(loveType);

        return LoveTestResultResponse.of(user, loveType);
    }
}
