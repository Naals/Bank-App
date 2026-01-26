package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.ProductDto;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.repository.ProductRepository;
import com.project.banksystemapp.repository.UserRepository;
import com.project.banksystemapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto, User user) {

        return null;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) {
        return null;
    }

    @Override
    public void deleteProduct(Long id, User user) {

    }

    @Override
    public List<ProductDto> getAllProductsByStoredId(User user) {
        return List.of();
    }

    @Override
    public List<ProductDto> searchByKeyword(User user) {
        return List.of();
    }
}
