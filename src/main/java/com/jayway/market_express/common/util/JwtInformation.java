package com.jayway.market_express.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtInformation {
    private String token;
    private String refreshToken;

    public static JwtInformation create(String token, String refreshToken) {
        return new JwtInformation(token, refreshToken);
    }
}
