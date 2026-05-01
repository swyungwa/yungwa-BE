package com.yungwa.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO: 실제 enum 타입으로 수정 필요
@Getter
@RequiredArgsConstructor
public enum LoveType {
    YANGBAN("양반", "신중/체면", "예의를 중시하고 밀당을 즐기며 천천히 타오르는 스타일"),
    JANGGUN("장군", "직진/용맹", "눈치 안 보고 돌진! 내 사람은 내가 지킨다는 파워 직진 스타일"),
    SATTO("사또", "주도/통제", "내가 계획한 대로 따라와야 직성이 풀리는 리더 스타일"),
    DOLSOE("돌쇠", "헌신/순정", "무조건적인 사랑. 궂은일도 다 해주는 조선 최고의 사랑꾼"),
    WANGJOK("왕족", "고귀/도도", "사랑받는 게 당연한 타입. 먼저 다가가기보다 기다리는 스타일"),
    GWANGDAE("광대", "재치/자유", "구속은 싫소! 입담과 유머로 상대를 매일 웃게 만드는 스타일");

    private final String displayName;
    private final String keyword;
    private final String description;
}
