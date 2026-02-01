package com.project.banksystemapp.controller;

import com.project.banksystemapp.payload.dto.InventoryDto;
import com.project.banksystemapp.payload.response.ApiResponse;
import com.project.banksystemapp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(
            @RequestBody InventoryDto inventoryDto
    ) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<InventoryDto> updateInventory(
            @RequestBody InventoryDto inventoryDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteInventory(
            @PathVariable Long id
    ) {
        inventoryService.deleteInventoryById(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted the inventory");

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(
            @PathVariable Long branchId
    ) {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }

    @GetMapping("/product/{productId}/branch/{branchId}")
    public ResponseEntity<InventoryDto> getInventoryByBranchAndProduct(
            @PathVariable Long branchId,
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
    }


}
