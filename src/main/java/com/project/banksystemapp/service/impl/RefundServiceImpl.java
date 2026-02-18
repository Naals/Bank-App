package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.RefundMapper;
import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Order;
import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.RefundDto;
import com.project.banksystemapp.repository.OrderRepository;
import com.project.banksystemapp.repository.RefundRepository;
import com.project.banksystemapp.service.RefundService;
import com.project.banksystemapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;

    @Override
    public RefundDto createRefund(Refund refund) throws UserException, Exception {
        User cashier = userService.getCurrentUser();

        Order order = orderRepository.findById(refund.getOrderId()).orElseThrow(
                () -> new Exception("Order not found with id: " + refund.getOrderId())
        );

        Branch branch = order.getBranch();
        Refund createBranch = Refund.builder()
                .order(order)
                .branch(branch)
                .cashier(cashier)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .createdAt(refund.getCreatedAt())
                .build();

        Refund savedRefund = refundRepository.save(createBranch);

        return RefundMapper.toDto(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefunds() {
        return refundRepository.findAll()
                .stream()
                .map(RefundMapper::toDto)
                .toList();
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId)
                .stream()
                .map(RefundMapper::toDto)
                .toList();
    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId) {
        return refundRepository.findByShiftReportId(shiftReportId)
                .stream()
                .map(RefundMapper::toDto)
                .toList();
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, startDate, endDate)
                .stream()
                .map(RefundMapper::toDto)
                .toList();
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) {
        return refundRepository.findByBranchId(branchId)
                .stream()
                .map(RefundMapper::toDto)
                .toList();
    }

    @Override
    public RefundDto getRefundById(Long refundId) {
        return refundRepository.findById(refundId)
                .map(RefundMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Refund not found with id: " + refundId));
    }

    @Override
    public void deleteRefund(Long refundId) {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);
    }
}
