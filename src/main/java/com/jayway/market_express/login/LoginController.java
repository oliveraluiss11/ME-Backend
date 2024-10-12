package com.jayway.market_express.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.LOGIN_PATH;
import static com.jayway.market_express.common.constant.PathConstant.OTP_PATH;

@RestController
@RequestMapping(LOGIN_PATH)
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping(OTP_PATH)
    public ResponseEntity<LoginOtpResponse> loginWithOtp(@RequestBody LoginOtpRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.loginWithOtp(request));
    }

}
