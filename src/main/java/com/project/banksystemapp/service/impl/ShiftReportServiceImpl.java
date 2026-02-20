package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.ShiftReportDto;
import com.project.banksystemapp.service.ShiftReportService;

import java.time.LocalDateTime;
import java.util.List;

public class ShiftReportServiceImpl implements ShiftReportService {
    @Override
    public ShiftReportDto startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) {
        return null;
    }

    @Override
    public ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) {
        return null;
    }

    @Override
    public ShiftReportDto getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDto> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDto getCurrentShiftProgress(Long cashierId) throws UserException {
        return null;
    }

    @Override
    public ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws UserException {
        return null;
    }
}
