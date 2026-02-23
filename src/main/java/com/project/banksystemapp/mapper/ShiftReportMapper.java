package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.Order;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.modal.ShiftReport;
import com.project.banksystemapp.payload.dto.OrderDto;
import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.payload.dto.RefundDto;
import com.project.banksystemapp.payload.dto.ShiftReportDto;
import java.util.Collections;

import java.util.List;

public final class ShiftReportMapper {

    private ShiftReportMapper() {}

    public static ShiftReportDto toDto(final ShiftReport shiftReport) {
        return ShiftReportDto.builder()
                .id(shiftReport.getId())
                .shiftStart(shiftReport.getShiftStart())
                .shiftEnd(shiftReport.getShiftEnd())
                .totalSales(shiftReport.getTotalSales())
                .totalOrders(shiftReport.getTotalOrders())
                .totalRefunds(shiftReport.getTotalRefunds())
                .netSale(shiftReport.getNetSale())
                .totalOrders(shiftReport.getTotalOrders())
                .cashier(UserMapper.toDto(shiftReport.getCashier()))
                .cashierId(shiftReport.getCashier().getId())
                .branchId(shiftReport.getBranch().getId())
                .recentOrders(mapOrders(shiftReport.getRecentOrders()))
                .topSellingProducts(mapProducts(shiftReport.getTopSellingProducts()))
                .refunds(mapRefunds(shiftReport.getRefunds()))
                .paymentSummaries(shiftReport.getPaymentSummaries())
                .build();
    }

    private static List<RefundDto> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()) return null;

        return refunds.stream().map(RefundMapper::toDto).toList();
    }

    private static List<ProductDto> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts == null || topSellingProducts.isEmpty()) return Collections.emptyList();

        return topSellingProducts.stream().map(ProductMapper::toDto).toList();
    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if(recentOrders == null || recentOrders.isEmpty()) return null;

        return recentOrders.stream().map(OrderMapper::toDto).toList();
    }

}
