package com.jayway.market_express.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusType {
    PENDING("PENDING", "Pendiente"),
    ACCEPTED_BY_STORE("ACCEPTED_BY_STORE", "Aceptado por la tienda"),
    ACCEPTED_BY_DRIVER("ACCEPTED_BY_DRIVER", "Aceptado por el repartidor"),
    REFUSED("REFUSED", "Rechazado"),
    PREPARING("PREPARING", "En preparación"),
    SHIPPED("SHIPPED", "En camino"),
    DELIVERED("DELIVERED", "Entregado");

    private final String code;
    private final String description;

}
