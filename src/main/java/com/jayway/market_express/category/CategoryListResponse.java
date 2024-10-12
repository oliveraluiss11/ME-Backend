package com.jayway.market_express.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryListResponse {
    private List<Category> categories;

    public static CategoryListResponse create(List<Category> categories) {
        return new CategoryListResponse(categories);
    }
}
