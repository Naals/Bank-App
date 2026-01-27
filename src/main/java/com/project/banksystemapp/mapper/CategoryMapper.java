package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.Category;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.payload.dto.CategoryDto;

public class CategoryMapper {

    private CategoryMapper() {}

    // ENTITY -> DTO
    public static CategoryDto toDto(Category category) {
        if (category == null) return null;

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();
    }

    // DTO -> ENTITY
    public static Category toEntity(CategoryDto dto, Store store) {
        if (dto == null) return null;

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .store(store) // используем store из аргумента
                .build();
    }

    // Обновление существующей сущности на основе DTO
    public static void updateEntity(Category category, CategoryDto dto) {
        if (category == null || dto == null) return;

        category.setName(dto.getName());
    }
}

