package com.project.banksystemapp.mapper;

import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.Store;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .image(product.getImage())
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    /**
     * Использовать для CREATE
     */
    public static Product toEntity(ProductDto dto, Store store) {
        if (dto == null) {
            return null;
        }

        return Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .mrp(dto.getMrp())
                .sellingPrice(dto.getSellingPrice())
                .brand(dto.getBrand())
                .image(dto.getImage())
                .store(store)
                .build();
    }

    /**
     * Использовать для UPDATE
     */
    public static void updateEntity(Product product, ProductDto dto) {
        if (product == null || dto == null) {
            return;
        }

        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getSku() != null) {
            product.setSku(dto.getSku());
        }
        if (dto.getMrp() != null) {
            product.setMrp(dto.getMrp());
        }
        if (dto.getSellingPrice() != null) {
            product.setSellingPrice(dto.getSellingPrice());
        }
        if (dto.getBrand() != null) {
            product.setBrand(dto.getBrand());
        }
        if (dto.getImage() != null) {
            product.setImage(dto.getImage());
        }
    }
}

