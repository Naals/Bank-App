package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.mapper.ProductMapper;
import com.project.banksystemapp.modal.Category;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.repository.CategoryRepository;
import com.project.banksystemapp.repository.ProductRepository;
import com.project.banksystemapp.repository.StoreRepository;
import com.project.banksystemapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(productDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));

        validateUserAccessToStore(user, store);

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        Product product = ProductMapper.toEntity(productDto, store, category);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        validateUserAccessToStore(user, product.getStore());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        ProductMapper.updateEntity(product, productDto, category);
        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        validateUserAccessToStore(user, product.getStore());

        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getAllProductsByStoredId(Long id) {
        return productRepository.findByStoreId(id)
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> searchByKeyword(Long id, String keyword) {
        return productRepository.searchByKeyword(id, keyword)
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    /* ==========================
       PRIVATE HELPER
       ========================== */

    private void validateUserAccessToStore(User user, Store store) {

        // Админ магазина
        if (store.getStoreAdmin().getId().equals(user.getId())) {
            return;
        }

        // Сотрудник магазина
        if (user.getStore() != null
                && user.getStore().getId().equals(store.getId())) {
            return;
        }

        throw new AccessDeniedException("Access denied to this store");
    }

}

