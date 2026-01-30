package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.BranchMapper;
import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.BranchDto;
import com.project.banksystemapp.repository.BranchRepository;
import com.project.banksystemapp.repository.StoreRepository;
import com.project.banksystemapp.service.BranchService;
import com.project.banksystemapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    /* ========= CREATE ========= */

    @Override
    public BranchDto createBranch(BranchDto dto, User user) throws UserException {

        User currentUser = userService.getCurrentUser();

        Store store = storeRepository.findByStoreAdminId(currentUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        Branch branch = BranchMapper.toEntity(dto, store);
        Branch saved = branchRepository.save(branch);

        return BranchMapper.toDto(saved);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto, User user) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        Store store = branch.getStore();

        BranchMapper.updateEntity(branch, branchDto, store, user);
        Branch updated = branchRepository.save(branch);

        return BranchMapper.toDto(updated);
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        branchRepository.delete(branch);
    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        return branchRepository.findAllByStore(store)
                .stream()
                .map(BranchMapper::toDto)
                .toList();
    }

    @Override
    public BranchDto getBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        return BranchMapper.toDto(branch);
    }
}
