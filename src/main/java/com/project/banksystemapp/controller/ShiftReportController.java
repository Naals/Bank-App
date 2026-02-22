package com.project.banksystemapp.controller;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.ShiftReportDto;
import com.project.banksystemapp.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDto> startShift() throws UserException {
        return ResponseEntity.ok(
                shiftReportService.startShift()
        );
    }

    @PatchMapping("/end")
    public ResponseEntity<ShiftReportDto> endShift() throws UserException {
        return ResponseEntity.ok(
                shiftReportService.endShift(null, null)
        );
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDto> getCurrentShiftProgress() throws UserException {
        return ResponseEntity.ok(
                shiftReportService.getCurrentShiftProgress(null)
        );
    }

    @GetMapping("/cashier/{cashierId}/current")
    public ResponseEntity<ShiftReportDto> getShiftReportByDate(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate
    ) throws UserException {
        return ResponseEntity.ok(
                shiftReportService.getShiftByCashierAndDate(cashierId, startDate)
        );
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByCashier(
            @PathVariable Long cashierId
    ) {
        return ResponseEntity.ok(
                shiftReportService.getShiftReportsByCashierId(cashierId)
        );
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByBranch(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(
                shiftReportService.getShiftReportsByBranchId(branchId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftReportDto> getShiftReportById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                shiftReportService.getShiftReportById(id)
        );
    }

    @GetMapping()
    public ResponseEntity<List<ShiftReportDto>> getAllShiftReports(
    ) {
        return ResponseEntity.ok(
                shiftReportService.getAllShiftReports()
        );
    }


}
