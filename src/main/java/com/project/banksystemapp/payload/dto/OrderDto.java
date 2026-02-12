package com.project.banksystemapp.payload.dto;

import com.project.banksystemapp.modal.Customer;
import com.project.banksystemapp.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDto branch;
    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDto> items;
}
