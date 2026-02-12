package com.project.banksystemapp.payload.dto;

import com.project.banksystemapp.modal.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {

    private Long id;

    private Integer quantity;

    private Double price;


    private Product product;

    private Long orderId;
}
