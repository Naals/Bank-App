package com.project.banksystemapp.controller;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.CategoryDto;
import com.project.banksystemapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    // CREATE
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto
    ) throws Exception, UserException {
        CategoryDto created = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(created);
    }

    // READ ALL BY STORE
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getAllByStoreId(@PathVariable Long storeId) {
        List<CategoryDto> categories = categoryService.getAllCategoriesByStoreId(storeId);
        return ResponseEntity.ok(categories);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDto categoryDto
    ) throws Exception, UserException {
        CategoryDto updated = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception, UserException {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

}
