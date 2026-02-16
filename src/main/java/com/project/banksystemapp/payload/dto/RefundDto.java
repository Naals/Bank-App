package com.project.banksystemapp.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDto {

    private Long id;

    private OrderDto order;
    private Long orderId;

    private String reason;

    private Double amount;

    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDto branch;
    private Long branchId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
