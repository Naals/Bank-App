package com.project.banksystemapp.controller;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.RefundDto;
import com.project.banksystemapp.payload.response.ApiResponse;
import com.project.banksystemapp.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refunds")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws UserException {
        RefundDto refund = refundService.createRefund(refundDto);
        return ResponseEntity.ok(refund);
    }

    @GetMapping
    public ResponseEntity<List<RefundDto>> getAllRefunds()  {
        List<RefundDto> refunds = refundService.getAllRefunds();
        return ResponseEntity.ok(refunds);
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<RefundDto>> getRefundsByCashierId(
            @PathVariable Long cashierId
    )  {
        return ResponseEntity.ok(refundService.getRefundByCashier(cashierId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDto>> getRefundsByBranchId(
            @PathVariable Long branchId
    )  {
        return ResponseEntity.ok(refundService.getRefundByBranch(branchId));
    }

    @GetMapping("/shift/{cashierId}")
    public ResponseEntity<List<RefundDto>> getRefundsByCashierIdAndDateRange(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    )  {
        return ResponseEntity.ok(refundService.getRefundByCashierAndDateRange(cashierId, startDate, endDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundById(
            @PathVariable Long id
    )  {
        return ResponseEntity.ok(refundService.getRefundById(id));
    }

    @GetMapping("/shift/{shiftId}")
    public ResponseEntity<List<RefundDto>> getRefundsByShiftId(
            @PathVariable Long shiftId
    ) {
        return ResponseEntity.ok(refundService.getRefundByShiftReport(shiftId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteRefund(
            @PathVariable Long id
    ) {
        refundService.deleteRefund(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Refund deleted successfully");
        return ResponseEntity.ok(apiResponse);

    }


}
