package com.yungwa.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_DUPLICATED_INSTAGRAM_ID(HttpStatus.CONFLICT, "이미 사용 중인 인스타그램 ID입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    DELETED_USER(HttpStatus.FORBIDDEN, "삭제된 사용자입니다."),
    LOVE_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 연애 유형 코드입니다."),
    INVALID_ADMIN_CODE(HttpStatus.UNAUTHORIZED, "관리자 코드가 올바르지 않습니다."),
    INSUFFICIENT_TICKETS(HttpStatus.PAYMENT_REQUIRED, "티켓이 부족합니다."),
    CANNOT_UNLOCK_SELF(HttpStatus.BAD_REQUEST, "자기 자신의 카드는 열람할 수 없습니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
