package com.kusitms.hotsixServer.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class UserConstants {
    @Getter
    @RequiredArgsConstructor
    public enum EBoardResponseMessage{
        LOGIN_SUCCESS("로그인에 성공했습니다"),
        LOGOUT_SUCCESS("로그아웃에 성공했습니다."),
        REGENERATE_TOKEN_SUCCESS("토큰 재발급에 성공했습니다.");
        private final String message;
    }
    @Getter
    @RequiredArgsConstructor
    public enum EToken{
        eRefreshToken("RT:");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum UserExceptionList {
        TOKEN_ERROR("400", HttpStatus.NOT_FOUND, "토큰 값을 다시 보내주세요."),
        NOT_FOUND_EMAIL("U0001", HttpStatus.NOT_FOUND, "해당 email을 가진 유저가 존재하지 않습니다"),
        NOT_FOUND_PASSWORD("U0002", HttpStatus.NOT_FOUND, "비밀번호가 잘못되었습니다."),
        OVERLAP_USER("U0003", HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다");

        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
