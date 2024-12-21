package com.jayway.market_express.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    CUSTOMER("CUSTOMER", "Cliente"),
    ADMIN("ADMIN", "Administrador"),
    STORE("STORE", "Tienda"),
    STORE_OWNER("STORE_OWNER", "Propietario de tienda"),
    RIDER("RIDER", "Repartidor");

    private final String code;
    private final String description;

}
