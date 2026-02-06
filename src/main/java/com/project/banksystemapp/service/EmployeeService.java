package com.project.banksystemapp.service;

import com.project.banksystemapp.domain.UserRole;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {

    UserDto createStoreEmployee(UserDto employee, Long storeId);
    UserDto createBranchEmployee(UserDto employee, Long branchId);
    User updateEmployee(UserDto employee, Long employeeId);
    void deleteEmployee(Long employeeId);
    List<UserDto> findStoreEmployees(Long storeId, UserRole role);
    List<UserDto> findBranchEmployees(Long branchId, UserRole role);

}
