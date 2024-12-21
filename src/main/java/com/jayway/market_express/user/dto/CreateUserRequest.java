package com.jayway.market_express.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserRequest {
    private String cellphone;
    private String documentNumber;
    private LocalDate birthDate;
    private String otp;
    private String address;
    private String district;
    private String province;
}
