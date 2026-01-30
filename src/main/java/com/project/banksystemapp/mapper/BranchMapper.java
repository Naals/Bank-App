package com.project.banksystemapp.mapper;


import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.BranchDto;

import java.time.LocalDateTime;

public class BranchMapper {

    private BranchMapper() {
    }

    /* ========= Entity → DTO ========= */

    public static BranchDto toDto(Branch branch) {
        if (branch == null) return null;

        BranchDto dto = new BranchDto();
        dto.setId(branch.getId());
        dto.setName(branch.getName());
        dto.setAddress(branch.getAddress());
        dto.setPhone(branch.getPhone());
        dto.setEmail(branch.getEmail());
        dto.setWorkingDays(branch.getWorkingDays());
        dto.setOpenTime(branch.getOpenTime());
        dto.setCloseTime(branch.getCloseTime());
        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());

        if (branch.getStore() != null) {
            dto.setStoreId(branch.getStore().getId());
        }


        return dto;
    }

    /* ========= Create DTO → Entity ========= */

    public static Branch toEntity(
            BranchDto dto,
            Store store
    ) {
        if (dto == null) return null;

        return Branch.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .workingDays(dto.getWorkingDays())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .store(store)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /* ========= Update Entity from DTO ========= */

    public static void updateEntity(
            Branch branch,
            BranchDto dto,
            Store store,
            User manager
    ) {
        if (branch == null || dto == null) return;

        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        branch.setPhone(dto.getPhone());
        branch.setEmail(dto.getEmail());
        branch.setWorkingDays(dto.getWorkingDays());
        branch.setOpenTime(dto.getOpenTime());
        branch.setCloseTime(dto.getCloseTime());

        if (store != null) {
            branch.setStore(store);
        }

        if (manager != null) {
            branch.setManager(manager);
        }
    }
}

