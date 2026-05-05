package com.yungwa.domain.lovetype.dto.response;

import com.yungwa.domain.lovetype.entity.LoveType;
import com.yungwa.domain.lovetype.entity.LoveTypeCode;
import com.yungwa.domain.user.entity.User;

public record LoveTestResultResponse(
        Long userId,
        String instagramId,
        LoveTypeCode typeCode,
        String nameKo,
        String description
) {
    public static LoveTestResultResponse of(User user, LoveType loveType) {
        return new LoveTestResultResponse(
                user.getId(),
                user.getInstagramId(),
                loveType.getTypeCode(),
                loveType.getNameKo(),
                loveType.getDescription()
        );
    }
}
