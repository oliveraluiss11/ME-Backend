package com.jayway.market_express.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateStoreRequest {
    private String name;
    private String cellphone;
    private String email;
    private String ruc;
    private String documentNumber;
    private LocalDate birthDate;
    private String location;
    private String province;
    private String district;
    private Map<String, DailyHours> openingHours;
    private String category;
}
