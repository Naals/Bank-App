package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.UserRole;
import com.project.banksystemapp.mapper.UserMapper;
import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.UserDto;
import com.project.banksystemapp.repository.BranchRepository;
import com.project.banksystemapp.repository.StoreRepository;
import com.project.banksystemapp.repository.UserRepository;
import com.project.banksystemapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String BRANCH_NOT_FOUND = "Branch not found with id:";

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) {
        Store store = storeRepository.findByStoreAdminId(storeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Store not found")
                );

        Branch branch = null;

        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null){
                throw new IllegalArgumentException("Branch ID is required to create branch manager");
            }
            branch = branchRepository.findById(employee.getBranchId())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Branch not found")
                    );
        }

        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setBranch(branch);
        user.setStore(store);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedUser = userRepository.save(user);

        if(savedUser.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            assert branch != null;
            branch.setManager(savedUser);
            branchRepository.save(branch);
        }

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto createBranchEmployee(UserDto employee, Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(
                        () -> new IllegalArgumentException(BRANCH_NOT_FOUND + branchId)
                );

        if(employee.getRole()==UserRole.ROLE_BRANCH_CASHIER ||
            employee.getRole()==UserRole.ROLE_BRANCH_MANAGER) {

            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDto(userRepository.save(user));
        }

        throw new IllegalArgumentException("Branch role not supported");
    }

    @Override
    public User updateEmployee(UserDto employee, Long employeeId) {
        User existingEmployee = userRepository.findById(employeeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id: " + employeeId)
                );

        Branch branch = branchRepository.findById(employee.getBranchId())
                .orElseThrow(
                        () -> new IllegalArgumentException(BRANCH_NOT_FOUND + employee.getBranchId())
                );

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setRole(employee.getRole());
        existingEmployee.setBranch(branch);

        return userRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        User existingEmployee = userRepository.findById(employeeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found with id: " + employeeId)
                );
        userRepository.delete(existingEmployee);
    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole role) {

        Store store = storeRepository.findByStoreAdminId(storeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Store not found")
                );

        return userRepository.findByStore(store).stream()
                .filter(
                        user -> role==null||user.getRole()==role
                ).map(
                        UserMapper::toDto
                )
                .toList();
    }

    @Override
    public List<UserDto> findBranchEmployees(Long branchId, UserRole role) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(
                        () -> new IllegalArgumentException(BRANCH_NOT_FOUND + branchId)
                );

        return userRepository.findByBranch(branch).stream()
                .filter(
                        user -> role==null||user.getRole()==role
                ).map(
                        UserMapper::toDto
                )
                .toList();
    }
}
