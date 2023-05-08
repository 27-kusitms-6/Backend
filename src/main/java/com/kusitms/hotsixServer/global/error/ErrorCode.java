package com.kusitms.hotsixServer.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
 * 에러 코드 관리
 */
@Getter
public enum ErrorCode {
    Token_Error(HttpStatus.BAD_REQUEST,400, "토큰값 다시 보내주세요."),
    SET_FILTER_ERROR(HttpStatus.BAD_REQUEST,400, "필터값 2개 이하로 보내주세요."),
    SET_CATEGORY1_ERROR(HttpStatus.BAD_REQUEST,400, "존재하는 카테고리값을 보내주세요.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;



    ErrorCode(HttpStatus httpStatus, int code, String message) { // BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

}