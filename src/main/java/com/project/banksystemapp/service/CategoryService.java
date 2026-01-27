package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws UserException, Exception;
    List<CategoryDto> getAllCategoriesByStoreId(Long storeId);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws UserException, Exception;
    void deleteCategory(Long id) throws Exception, UserException;
}
