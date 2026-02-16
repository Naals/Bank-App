package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.Refund;
import com.project.banksystemapp.payload.dto.RefundDto;
import org.springframework.stereotype.Component;

@Component
public class RefundMapper {

    public RefundDto toDto(Refund refund) {
        if (refund == null) {
            return null;
        }

        return RefundDto.builder()
                .id(refund.getId())
                .orderId(
                        refund.getOrder() != null
                                ? refund.getOrder().getId()
                                : null
                )
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .shiftReportId(
                        refund.getShiftReport() != null
                                ? refund.getShiftReport().getId()
                                : null
                )
                .cashierName(
                        refund.getCashier() != null
                                ? refund.getCashier().getFullName()
                                : null
                )
                .branchId(
                        refund.getBranch() != null
                                ? refund.getBranch().getId()
                                : null
                )
                .createdAt(refund.getCreatedAt())
                .updatedAt(refund.getUpdatedAt())
                .build();
    }
}
