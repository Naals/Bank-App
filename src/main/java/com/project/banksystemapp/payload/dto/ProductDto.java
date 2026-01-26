package com.project.banksystemapp.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
