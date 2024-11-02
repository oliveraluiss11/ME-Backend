package com.jayway.market_express.user.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.jayway.market_express.common.constant.LocationConstant.DEFAULT_COUNTRY;
import static com.jayway.market_express.common.constant.LocationConstant.DEFAULT_REGION;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAddressDto {
    private String location;
    private String district;
    private String province;
    private String region;
    private String country;

    public static UserAddressDto create(String location, String district, String province){
        return new UserAddressDto(location, district, province, DEFAULT_REGION, DEFAULT_COUNTRY);
    }
}
