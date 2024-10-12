package com.jayway.market_express.otp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryCodeType {
    PE("+51", "Peru");

    private final String code;
    private final String name;
}
