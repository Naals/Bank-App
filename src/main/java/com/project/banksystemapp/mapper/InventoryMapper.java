package com.project.banksystemapp.mapper;


import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Inventory;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.payload.dto.InventoryDto;

import java.time.LocalDateTime;

public final class InventoryMapper {

    public InventoryDto toDto(Inventory inventory) {
        if (inventory == null) return null;

        return InventoryDto.builder()
                .id(inventory.getId())
                .branch(BranchMapper.toDto(inventory.getBranch()))
                .product(ProductMapper.toDto(inventory.getProduct()))
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .quantity(inventory.getQuantity())
                .lastUpdate(inventory.getLastUpdate())
                .build();
    }

    public Inventory toEntity(InventoryDto inventoryDto,
                              Branch branch,
                              Product product
    ) {
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .lastUpdate(LocalDateTime.now())
                .build();
    }

    public void updateEntity(Inventory inventory, Integer quantity) {
        inventory.setQuantity(quantity);
        inventory.setLastUpdate(LocalDateTime.now());
    }

}
