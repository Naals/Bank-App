package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.OrderItem;
import com.project.banksystemapp.payload.dto.OrderItemDto;

public final class OrderItemMapper {


    private OrderItemMapper() {
    }

    public static OrderItemDto toDto(OrderItem orderItem) {
        if (orderItem == null) return null;

        return OrderItemDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .product(ProductMapper.toDto(orderItem.getProduct()))
                .orderId(orderItem.getOrder() != null ? orderItem.getOrder().getId() : null)
                .productId(orderItem.getProduct() != null ? orderItem.getProduct().getId() : null)
                .build();
    }

}

