package com.yungwa.domain.user.dto.response;

import com.yungwa.domain.lovetype.entity.LoveTypeCode;
import com.yungwa.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record MyProfileResponse(

        @Schema(description = "사용자 ID")
        Long userId,

        @Schema(description = "인스타그램 ID")
        String instagramId,

        @Schema(description = "성별")
        String gender,

        @Schema(description = "MBTI")
        String mbti,

        @Schema(description = "한 줄 자기소개")
        String introduction,

        @Schema(description = "대표 이모지")
        String emoji,

        @Schema(description = "보유 티켓 수")
        int ticketCount,

        @Schema(description = "연애 유형 코드")
        LoveTypeCode loveTypeCode,

        @Schema(description = "연애 유형 한글명")
        String loveTypeName
) {
    public static MyProfileResponse from(User user) {
        return new MyProfileResponse(
                user.getId(),
                user.getInstagramId(),
                user.getGender().name(),
                user.getMbti(),
                user.getIntroduction(),
                user.getEmoji(),
                user.getTicketCount(),
                user.getLoveType() != null ? user.getLoveType().getTypeCode() : null,
                user.getLoveType() != null ? user.getLoveType().getNameKo() : null
        );
    }
}
