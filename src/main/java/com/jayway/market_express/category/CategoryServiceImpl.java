package com.jayway.market_express.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryListResponse getCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<Category> categoryList = categoryEntityList.stream()
                .map(categoryEntity -> Category.create(categoryEntity.getCode(), categoryEntity.getName()))
                .toList();
        return CategoryListResponse.create(categoryList);
    }
}
