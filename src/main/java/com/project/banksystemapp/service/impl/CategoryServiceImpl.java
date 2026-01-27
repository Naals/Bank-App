package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.UserRole;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.CategoryMapper;
import com.project.banksystemapp.modal.Category;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.CategoryDto;
import com.project.banksystemapp.repository.CategoryRepository;
import com.project.banksystemapp.repository.StoreRepository;
import com.project.banksystemapp.service.CategoryService;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception, UserException {

        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(categoryDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));

        Category category = Category.builder()
                .name(categoryDto.getName())
                .store(store)
                .build();

        checkAuthority(user, category.getStore());

        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategoriesByStoreId(Long storeId) {
        return categoryRepository.findByStoreId(storeId)
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws UserException, Exception {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found with id: " + id));
        User user = userService.getCurrentUser();

        checkAuthority(user, category.getStore());

        CategoryMapper.updateEntity(category, categoryDto);
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception, UserException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found with id: " + id));
        User user = userService.getCurrentUser();

        checkAuthority(user, category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("You do not have permission to manage this category");
        }

    }
}
