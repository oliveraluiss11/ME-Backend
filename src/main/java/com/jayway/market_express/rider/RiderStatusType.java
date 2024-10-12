package com.jayway.market_express.rider;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiderStatusType {
    AVAILABLE("AVAILABLE", "Disponible para pedidos"),
    ASSIGNED("ASSIGNED", "Asignado a un pedido"),
    ON_DELIVERY("ON_DELIVERY", "En entrega"),
    UNAVAILABLE("UNAVAILABLE", "No disponible");

    private final String code;
    private final String name;
}
