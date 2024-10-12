package com.jayway.market_express.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtPayload {
    private String cellphone;
    private String role;

    public static JwtPayload create(String cellphone, String role) {
        return new JwtPayload(cellphone, role);
    }
}
