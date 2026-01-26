package com.project.banksystemapp.service;

import com.project.banksystemapp.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategoriesByStoreId(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
