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
    List<User> findStoreEmployees(Long storeId, UserRole role);
    List<User> findBranchEmployees(Long branchId, UserRole role);

}
