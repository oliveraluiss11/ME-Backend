package com.jayway.market_express.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    CUSTOMER("CUSTOMER", "Cliente"),
    ADMIN("ADMIN", "Administrador"),
    STORE("STORE", "Tienda"),
    RIDER("RIDER", "Repartidor");

    private final String code;
    private final String description;

}
