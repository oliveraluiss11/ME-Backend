package com.jayway.market_express.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EntityStatusType {
    ACTIVE("ACTIVE", "Activo"),
    USED("USED", "Usado"),
    INACTIVE("INACTIVE", "Inactivo"),
    DISABLED("DISABLED", "Desactivado"),
    BLOCKED("BLOCKED", "Bloqueado"),
    EXPIRED("EXPIRED", "Expirado");

    private final String code;
    private final String description;
}
