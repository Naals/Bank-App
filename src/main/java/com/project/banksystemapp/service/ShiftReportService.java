package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.ShiftReportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDto startShift(Long cashierId, Long branchId,
                              LocalDateTime shiftStart) throws UserException;

    ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws UserException;

    ShiftReportDto getShiftReportById(Long id);

    List<ShiftReportDto> getAllShiftReports();

    List<ShiftReportDto> getShiftReportsByBranchId(Long branchId);
    List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId);

    ShiftReportDto getCurrentShiftProgress(Long cashierId) throws UserException;

    ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws UserException;
}
