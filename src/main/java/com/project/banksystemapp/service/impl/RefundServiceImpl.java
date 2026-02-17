package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.payload.dto.RefundDto;
import com.project.banksystemapp.service.RefundService;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final UserService userService;
    @Override
    public RefundDto createRefund(Refund refund) {
        return null;
    }

    @Override
    public List<RefundDto> getAllRefunds() {
        return List.of();
    }

    @Override
    public RefundDto getRefundByCashier(Long cashierId) {
        return null;
    }

    @Override
    public RefundDto getRefundByShiftReport(Long shiftReportId) {
        return null;
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {
        return List.of();
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) {
        return List.of();
    }

    @Override
    public RefundDto getRefundById(Long refundId) {
        return null;
    }

    @Override
    public void deleteRefund(Long refundId) {

    }
}
