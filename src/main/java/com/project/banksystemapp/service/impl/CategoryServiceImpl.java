package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.payload.dto.CategoryDto;
import com.project.banksystemapp.repository.CategoryRepository;
import com.project.banksystemapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategoriesByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
