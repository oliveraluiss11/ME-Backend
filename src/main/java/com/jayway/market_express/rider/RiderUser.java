package com.jayway.market_express.rider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RiderUser {
    private String userId;
    private String documentNumber;
    private String cellphone;

    public static RiderUser create(String userId, String documentNumber, String cellphone) {
        return new RiderUser(userId, documentNumber, cellphone);
    }
}
