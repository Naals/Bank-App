package com.project.banksystemapp.service;

import com.project.banksystemapp.domain.ProductDto;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.User;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user);
    ProductDto updateProduct(Long id, ProductDto productDto, User user);
    void deleteProduct(Long id, User user);
    List<ProductDto> getAllProductsByStoredId(User user);
    List<ProductDto> searchByKeyword(User user);
}
