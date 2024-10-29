package com.jayway.market_express.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Category {
    private String code;
    private String name;
    private String type;

    public static Category create(String code, String name, String type) {
        return new Category(code, name,type);
    }
}
