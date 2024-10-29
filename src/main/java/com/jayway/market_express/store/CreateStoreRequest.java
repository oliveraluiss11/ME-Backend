package com.jayway.market_express.store;

import java.util.Map;

public class CreateStoreRequest {
    private String name;
    private String cellphone;
    private String email;
    private String ruc;
    private StoreAddressDto address;
    private Map<String, DailyHours> openingHours;
    private String category;
}
