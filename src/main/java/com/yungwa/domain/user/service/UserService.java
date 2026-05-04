package com.yungwa.domain.user.service;

import com.yungwa.domain.user.dto.request.MyProfileUpdateRequest;
import com.yungwa.domain.user.dto.response.MyProfileResponse;
import com.yungwa.domain.user.entity.User;
import com.yungwa.domain.user.repository.UserRepository;
import com.yungwa.global.exception.CustomException;
import com.yungwa.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyProfileResponse getMyProfile(Long userId) {
        User user = userRepository.findByIdWithLoveType(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return MyProfileResponse.from(user);
    }

    @Transactional
    public MyProfileResponse updateMyProfile(Long userId, MyProfileUpdateRequest request) {
        User user = userRepository.findByIdWithLoveType(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.mbti(), request.introduction(), request.emoji());

        return MyProfileResponse.from(user);
    }
}
