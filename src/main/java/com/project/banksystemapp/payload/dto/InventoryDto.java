package com.project.banksystemapp.payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryDto {

    private Long id;

    private BranchDto branch;

    private ProductDto product;

    private Integer quantity;

    private LocalDateTime lastUpdate;
}
