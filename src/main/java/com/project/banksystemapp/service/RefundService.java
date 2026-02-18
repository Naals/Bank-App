package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDto createRefund(Refund refund) throws UserException, Exception;

    List<RefundDto> getAllRefunds();

    List<RefundDto> getRefundByCashier(Long cashierId);

    List<RefundDto> getRefundByShiftReport(Long shiftReportId);

    List<RefundDto> getRefundByCashierAndDateRange(Long cashierId,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate);

    List<RefundDto> getRefundByBranch(Long branchId);

    RefundDto getRefundById(Long refundId);

    void deleteRefund(Long refundId);
}
