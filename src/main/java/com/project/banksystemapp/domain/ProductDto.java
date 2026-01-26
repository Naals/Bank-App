package com.project.banksystemapp.domain;

import com.project.banksystemapp.modal.Store;

import java.time.LocalDateTime;

public class ProductDto {

    private Long id;

    private String name;

    private String sku;

    private Double mrp;

    private Double sellingPrice;

    private String brand;
    private String image;

    private Long storeId;
    private Long categoryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
