package com.project.banksystemapp.service;

import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDto createRefund(Refund refund);

    List<RefundDto> getAllRefunds();

    RefundDto getRefundByCashier(Long cashierId);

    RefundDto getRefundByShiftReport(Long shiftReportId);

    List<RefundDto> getRefundByCashierAndDateRange(Long cashierId,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate);

    List<RefundDto> getRefundByBranch(Long branchId);

    RefundDto getRefundById(Long refundId);

    void deleteRefund(Long refundId);
}
