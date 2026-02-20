package com.project.banksystemapp.payload.dto;

import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.PaymentSummary;
import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.modal.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReportDto {

    private Long id;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    private Double totalSales;
    private Double totalRefunds;
    private Double netSale;
    private int totalOrders;

    private UserDto cashier;
    private Long cashierId;
    private String cashierName;

    private BranchDto branch;
    private Long branchId;
    private String branchName;

    private List<PaymentSummary> paymentSummaries;
    private List<ProductDto> topSellingProducts;
    private List<OrderDto> recentOrders;
    private List<RefundDto> refunds;
}
