package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Inventory;
import com.project.banksystemapp.payload.dto.InventoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByProductIdAndBranchId(Long productId, Long brandId);
    List<Inventory> findByBranchId(Long branchId);
}
