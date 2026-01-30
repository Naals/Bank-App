package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.BranchDto;
import com.project.banksystemapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    @Override
    public BranchDto createBranch(BranchDto branchDto, User user) {
        return null;
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto, User user) {
        return null;
    }

    @Override
    public BranchDto deleteBranch(Long id) {
        return null;
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {
        return List.of();
    }

    @Override
    public BranchDto getBranchById(Long id) {
        return null;
    }
}
