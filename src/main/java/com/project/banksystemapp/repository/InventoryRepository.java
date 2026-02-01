package com.project.banksystemapp.repository;

import com.project.banksystemapp.modal.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByProductIdAndBranchId(Long productId, Long brandId);
    List<Inventory> findByBranchId(Long branchId);
}
