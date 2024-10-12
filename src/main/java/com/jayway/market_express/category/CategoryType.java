package com.jayway.market_express.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryType {
    GROCERIES("GROCERIES", "Abarrotes"),
    SNACKS("SNACKS", "Snacks"),
    WATERS("WATERS", "Aguas"),
    DRINKS("DRINKS", "Bebidas no alcohólicas"),
    PERSONAL_CARE("PERSONAL_CARE", "Cuidado Personal"),
    HOUSEHOLD_PRODUCTS("HOUSEHOLD_PRODUCTS", "Productos de Limpieza del Hogar"),
    BABY_PRODUCTS("BABY_PRODUCTS", "Productos para Bebés"),
    PET_PRODUCTS("PET_PRODUCTS", "Productos para Mascotas");

    private final String code;
    private final String name;
}

