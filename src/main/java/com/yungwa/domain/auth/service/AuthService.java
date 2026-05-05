package com.yungwa.domain.auth.service;

import com.yungwa.domain.auth.dto.request.LoginRequest;
import com.yungwa.domain.auth.dto.request.SignupRequest;
import com.yungwa.domain.auth.dto.response.LoginResponse;
import com.yungwa.domain.auth.dto.response.SignupResponse;
import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.lovetype.repository.LoveTypeRepository;
import com.yungwa.domain.user.entity.User;
import com.yungwa.domain.user.repository.UserRepository;
import com.yungwa.global.exception.CustomException;
import com.yungwa.global.exception.ErrorCode;
import com.yungwa.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final LoveTypeRepository loveTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(SignupRequest signupRequest) {
        if (userRepository.existsByInstagramId(signupRequest.instagramId())) {
            throw new CustomException(ErrorCode.USER_DUPLICATED_INSTAGRAM_ID);
        }

        LoveType loveType = loveTypeRepository.findByTypeCode(signupRequest.loveTypeCode())
                .orElseThrow(() -> new CustomException(ErrorCode.LOVE_TYPE_NOT_FOUND));

        User user = User.builder()
                .instagramId(signupRequest.instagramId())
                .password(passwordEncoder.encode(signupRequest.password()))
                .gender(signupRequest.gender())
                .mbti(signupRequest.mbti())
                .introduction(signupRequest.introduction())
                .emoji(signupRequest.emoji())
                .build();

        user.updateLoveType(loveType);
        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getId());
        return new SignupResponse(
                savedUser.getId(),
                savedUser.getInstagramId(),
                savedUser.getGender().name(),
                savedUser.getMbti(),
                savedUser.getIntroduction(),
                savedUser.getEmoji(),
                loveType.getTypeCode(),
                loveType.getNameKo(),
                token
        );
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByInstagramId(loginRequest.instagramId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.isDeleted()) {
            throw new CustomException(ErrorCode.DELETED_USER);
        }

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(user.getId());
        return new LoginResponse(user.getId(), user.getInstagramId(), token);
    }
}
