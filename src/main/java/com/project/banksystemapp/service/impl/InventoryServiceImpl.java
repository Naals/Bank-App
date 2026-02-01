package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.mapper.InventoryMapper;
import com.project.banksystemapp.modal.Branch;
import com.project.banksystemapp.modal.Inventory;
import com.project.banksystemapp.modal.Product;
import com.project.banksystemapp.payload.dto.InventoryDto;
import com.project.banksystemapp.repository.BranchRepository;
import com.project.banksystemapp.repository.InventoryRepository;
import com.project.banksystemapp.repository.ProductRepository;
import com.project.banksystemapp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    private static final String INVENTORY_NOT_FOUND = "Inventory not found";

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Branch not found")
                );

        Product product = productRepository.findById(inventoryDto.getProductId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Product not found")
                );

        Inventory inventory = InventoryMapper.toEntity(inventoryDto, branch, product);

        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(INVENTORY_NOT_FOUND)
                );

        InventoryMapper.updateEntity(inventory, inventoryDto);


        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(INVENTORY_NOT_FOUND)
                );

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public void deleteInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(INVENTORY_NOT_FOUND)
                );

        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventoryList = inventoryRepository.findByBranchId(branchId);

        return inventoryList.stream()
                .map(InventoryMapper::toDto)
                .toList();
    }
}
