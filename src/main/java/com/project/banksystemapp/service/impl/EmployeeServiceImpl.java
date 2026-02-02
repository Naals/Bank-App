package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.UserRole;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) {
        return null;
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) {
        return null;
    }

    @Override
    public User updateEmployee(UserDto employee, Long employeeId) {
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {

    }

    @Override
    public List<User> findStoreEmployees(Long storeId, UserRole role) {
        return List.of();
    }

    @Override
    public List<User> findBranchEmployees(Long branchId, UserRole role) {
        return List.of();
    }
}
