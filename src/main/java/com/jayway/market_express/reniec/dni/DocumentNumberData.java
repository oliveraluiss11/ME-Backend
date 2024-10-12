package com.jayway.market_express.reniec.dni;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DocumentNumberData {
    @JsonProperty(DocumentNumberConstant.NUMBER)
    private String number;
    @JsonProperty(DocumentNumberConstant.FULL_NAME)
    private String fullName;
    @JsonProperty(DocumentNumberConstant.NAMES)
    private String names;
    @JsonProperty(DocumentNumberConstant.FIRST_LAST_NAME)
    private String firstLastName;
    @JsonProperty(DocumentNumberConstant.SECOND_LAST_NAME)
    private String secondLastName;
    @JsonProperty(DocumentNumberConstant.VERIFICATION_CODE)
    private String verificationCode;
}
