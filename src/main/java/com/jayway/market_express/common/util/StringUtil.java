package com.jayway.market_express.common.util;

import com.jayway.market_express.otp.CountryCodeType;

import static com.jayway.market_express.common.constant.SecurityConstant.BEARER_PREFIX;

public class StringUtil {
    public static String buildConstantMessageFromText(String text, String message) {
        return String.format(message, text);
    }

    public static String formatCellphoneFromPeru(String cellphone) {
        return CountryCodeType.PE.getCode() + cellphone;
    }

    public static String buildBearerToken(String token) {
        return BEARER_PREFIX + token;
    }
}
