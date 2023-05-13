package com.kusitms.hotsixServer.domain.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kusitms.hotsixServer.domain.user.constant.UserConstants;
import com.kusitms.hotsixServer.domain.user.dto.GoogleUser;
import com.kusitms.hotsixServer.domain.user.dto.IdTokenDto;
import com.kusitms.hotsixServer.domain.user.dto.UserDto;
import com.kusitms.hotsixServer.domain.user.service.OauthService;
import com.kusitms.hotsixServer.global.dto.ResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class OauthController {

    private final OauthService oauthService;

    @ApiOperation("구글 로그인 테스트용")
    @GetMapping(value="/{socialLoginType}")
    public void socialLoginType(@PathVariable(name="socialLoginType") String socialLoginType) throws IOException {
        oauthService.request(socialLoginType);
    }

    @ApiOperation("구글 로그인")
    @GetMapping(value="/google/callback")
    public ResponseEntity<ResponseDto<UserDto.socialLoginResponse>> callback (@RequestParam(name="code") String code) throws JsonProcessingException {
        return ResponseEntity.ok(ResponseDto.create(
                UserConstants.EBoardResponseMessage.LOGIN_SUCCESS.getMessage(),
                this.oauthService.getUserInfo(code)));

    }

    @ApiOperation("ID_TOKEN 요청 API")
    @PostMapping (value="/google/idToken")
    public ResponseEntity<ResponseDto<UserDto.socialLoginResponse>> signUp (@RequestBody IdTokenDto idTokenDto) throws IOException, GeneralSecurityException {
        return ResponseEntity.ok(ResponseDto.create(
                UserConstants.EBoardResponseMessage.LOGIN_TEST_SUCCESS.getMessage(),
                this.oauthService.appGoogleLogin(idTokenDto)));

    }
}
