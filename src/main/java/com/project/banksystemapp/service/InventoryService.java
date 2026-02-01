package com.project.banksystemapp.service;

import com.project.banksystemapp.payload.dto.InventoryDto;

import java.util.List;


public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto);
    InventoryDto updateInventory(Long id, InventoryDto inventoryDto);
    InventoryDto getInventoryById(Long id);
    void deleteInventoryById(Long id);
    InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    List<InventoryDto> getAllInventoryByBranchId(Long branchId);
}
