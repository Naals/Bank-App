package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.payload.dto.InventoryDto;
import com.project.banksystemapp.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        return null;
    }

    @Override
    public InventoryDto updateInventory(InventoryDto inventoryDto) {
        return null;
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        return null;
    }

    @Override
    public void deleteInventoryById(Long id) {

    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        return null;
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        return List.of();
    }
}
