package com.project.banksystemapp.service;

import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.modal.User;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto, User user) throws Exception;
    ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;
    void deleteProduct(Long id, User user);
    List<ProductDto> getAllProductsByStoredId(Long id);
    List<ProductDto> searchByKeyword(Long id, String keyword);
}
