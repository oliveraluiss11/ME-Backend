package com.jayway.market_express.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginOtpRequest {
    private String cellphone;
    private String otp;
}
