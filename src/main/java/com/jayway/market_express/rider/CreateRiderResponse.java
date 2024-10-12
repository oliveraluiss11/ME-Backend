package com.jayway.market_express.rider;

import com.jayway.market_express.common.util.JwtInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRiderResponse {
    private String fullName;
    private String photoUrl;
    private String role;
    private JwtInformation jwtInformation;

    public static CreateRiderResponse create(String fullName, String photoUrl, String role, String token, String refreshToken) {
        JwtInformation jwtInformation = JwtInformation.create(token, refreshToken);
        return new CreateRiderResponse(fullName, photoUrl, role, jwtInformation);
    }
}
