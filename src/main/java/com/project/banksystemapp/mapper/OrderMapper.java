package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.*;
import com.project.banksystemapp.payload.dto.OrderDto;

import java.util.stream.Collectors;

public class OrderMapper {

    private OrderMapper() {}

    public static OrderDto toDto(Order order) {
        if (order == null) return null;

        return OrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch() != null ? order.getBranch().getId() : null)
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                .branch(BranchMapper.toDto(order.getBranch()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .items(order.getItems() != null
                        ? order.getItems().stream()
                        .map(OrderItemMapper::toDto)
                        .toList()
                        : null)
                .build();
    }
}

