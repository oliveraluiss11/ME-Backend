package com.jayway.market_express.user;

import com.jayway.market_express.common.util.JwtInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserResponse {
    private String fullName;
    private String photoUrl;
    private String role;
    private JwtInformation jwtInformation;

    public static CreateUserResponse create(String fullName, String photoUrl, String role, String token, String refreshToken) {
        JwtInformation jwtInformation = JwtInformation.create(token, refreshToken);
        return new CreateUserResponse(fullName, photoUrl, role, jwtInformation);
    }
}
