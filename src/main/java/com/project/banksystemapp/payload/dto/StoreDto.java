package com.project.banksystemapp.payload.dto;

import com.project.banksystemapp.domain.StroreStatus;
import com.project.banksystemapp.modal.StoreContact;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;

    private String brand;

    private String phone;

    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StroreStatus status;

    private StoreContact contact;
}
