package com.jayway.market_express.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryListResponse getCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<Category> categoryList = categoryEntityList.stream()
                .map(categoryEntity -> Category.create(categoryEntity.getCode(), categoryEntity.getName(), categoryEntity.getType()))
                .toList();
        return CategoryListResponse.create(categoryList);
    }

    @Override
    public void createCategory(CategoryListRequest request) {
        List<CategoryEntity> categoryList = categoryRepository.findAll()
                .stream()
                .filter(categoryEntity -> Objects.isNull(categoryEntity.getType()))
                .peek(categoryEntity -> categoryEntity.setType("PRODUCT"))
                .toList();

        /*List<CategoryEntity> categoryList = request.getCategories()
                .stream()
                .map(CategoryEntity::from)
                .toList();*/
        categoryRepository.saveAll(categoryList);
    }
}
