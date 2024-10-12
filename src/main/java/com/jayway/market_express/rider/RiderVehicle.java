package com.jayway.market_express.rider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RiderVehicle {
    private String type;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private RiderLicense license;
}
