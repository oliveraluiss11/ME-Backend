package com.jayway.market_express.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.CATEGORY_PATH;

@RestController
@RequestMapping(CATEGORY_PATH)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryListResponse> getCategories() {
        CategoryListResponse categories = categoryService.getCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
