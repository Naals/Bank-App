package com.project.banksystemapp.payload.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BranchDto {

    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;

    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    private Long storeId;

    private StoreDto store;
    private UserDto manager;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
