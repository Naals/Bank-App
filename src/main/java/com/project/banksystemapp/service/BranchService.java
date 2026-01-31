package com.project.banksystemapp.service;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto createBranch(BranchDto branchDto) throws UserException;
    BranchDto updateBranch(Long id, BranchDto branchDto);
    void deleteBranch(Long id);
    List<BranchDto> getAllBranchesByStoreId(Long storeId);
    BranchDto getBranchById(Long id);
}
