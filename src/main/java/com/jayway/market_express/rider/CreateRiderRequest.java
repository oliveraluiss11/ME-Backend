package com.jayway.market_express.rider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateRiderRequest {
    private String cellphone;
    private String documentNumber;
    private String fullName;
    private LocalDate birthDate;
    private String otp;
    private RiderVehicle vehicle;
}
