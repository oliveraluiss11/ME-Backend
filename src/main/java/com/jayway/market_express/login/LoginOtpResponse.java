package com.jayway.market_express.login;

import com.jayway.market_express.common.util.JwtInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginOtpResponse {
    private String fullName;
    private String photoUrl;
    private String role;
    private JwtInformation jwt;

    public static LoginOtpResponse create(String fullName, String photoUrl, String role, String token, String refreshToken) {
        JwtInformation jwtInformation = JwtInformation.create(token, refreshToken);
        return new LoginOtpResponse(fullName, photoUrl, role, jwtInformation);
    }
}
