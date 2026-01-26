package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.UserRole;
import com.project.banksystemapp.mapper.ProductMapper;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.modal.User;
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
    private final StoreRepository storeRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(productDto.getStoreId())
                .orElseThrow(() -> new Exception("Store not found"));

        validateUserAccessToStore(user, store);

        Product product = ProductMapper.toEntity(productDto, store);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));

        validateUserAccessToStore(user, product.getStore());

        ProductMapper.updateEntity(product, productDto);
        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        validateUserAccessToStore(user, product.getStore());

        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getAllProductsByStoredId(User user) {
        Store store = getStoreForUser(user);

        return productRepository.findByStoreId(store.getId())
                .stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> searchByKeyword(User user, String keyword) {
        Store store = getStoreForUser(user);


        return productRepository.searchByKeyword(store.getId(), keyword)
                .stream()
                .map(ProductMapper::toProductDto)
                .toList();
    }

    /* ==========================
       PRIVATE HELPERS
       ========================== */

    private Store getStoreForUser(User user) {

        // Если пользователь — админ магазина
        if (user.getRole() == UserRole.ROLE_STORE_MANAGER
                || user.getRole() == UserRole.ROLE_ADMIN) {

            return storeRepository.findByStoreAdminId(user.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Store not found for admin"));
        }

        // Если сотрудник
        if (user.getStore() != null) {
            return user.getStore();
        }

        throw new AccessDeniedException("User is not assigned to any store");
    }


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

