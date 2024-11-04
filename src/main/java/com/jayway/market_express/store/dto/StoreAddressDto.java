package com.jayway.market_express.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.jayway.market_express.common.constant.LocationConstant.DEFAULT_REGION;
import static com.jayway.market_express.common.constant.LocationConstant.DEFAULT_COUNTRY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreAddressDto {
    private String location;

    private String district;
    private String province;
    private String region;
    private String country;

    public static StoreAddressDto create(String location, String district, String province){
        return new StoreAddressDto(location, district, province, DEFAULT_REGION, DEFAULT_COUNTRY);
    }

    public static StoreAddressDto from(CreateStoreRequest request){
        return new StoreAddressDto(request.getLocation(), request.getDistrict(), request.getProvince(), DEFAULT_REGION, DEFAULT_COUNTRY);
    }
}
