package com.jayway.market_express.rider;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class RiderOrder {
    private Integer completed;
    private Integer refused;
    private Integer cancelled;

    public static RiderOrder create() {
        return new RiderOrder(0, 0, 0);
    }
}
