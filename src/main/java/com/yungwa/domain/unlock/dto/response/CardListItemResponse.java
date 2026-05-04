package com.yungwa.domain.unlock.dto.response;

import com.yungwa.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record CardListItemResponse(

        @Schema(description = "사용자 ID")
        Long userId,

        @Schema(description = "인스타그램 ID (미열람 시 'LOCKED')")
        String instagramId,

        @Schema(description = "성별")
        String gender,

        @Schema(description = "MBTI")
        String mbti,

        @Schema(description = "한 줄 자기소개")
        String introduction,

        @Schema(description = "대표 이모지")
        String emoji,

        @Schema(description = "연애 유형 코드")
        String loveTypeCode,

        @Schema(description = "연애 유형 한글명")
        String loveTypeName
) {
    public static CardListItemResponse of(User user, boolean isUnlocked) {
        return new CardListItemResponse(
                user.getId(),
                isUnlocked ? user.getInstagramId() : "LOCKED",
                user.getGender().name(),
                user.getMbti(),
                user.getIntroduction(),
                user.getEmoji(),
                user.getLoveType() != null ? user.getLoveType().getTypeCode() : null,
                user.getLoveType() != null ? user.getLoveType().getNameKo() : null
        );
    }
}
