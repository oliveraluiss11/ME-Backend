package com.jayway.market_express.common.util;

import com.jayway.market_express.otp.CountryCodeType;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    public static String toTitleCase(String text) {
        return Arrays.stream(text.split(" "))       // Divide el texto en palabras
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())  // Capitaliza la primera letra de cada palabra
                .collect(Collectors.joining(" "));  // Une las palabras con un espacio
    }
}
