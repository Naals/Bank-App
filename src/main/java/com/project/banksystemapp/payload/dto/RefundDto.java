package com.project.banksystemapp.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Order;
import com.project.banksystemapp.modal.ShiftReport;
import com.project.banksystemapp.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDto {

    private Long id;

    private OrderDto order;

    private String reason;

    private Double amount;

    private Long shiftReportId;

    private UserDto cashier;

    private BranchDto branch;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
