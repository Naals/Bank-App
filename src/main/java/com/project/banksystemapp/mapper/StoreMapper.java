package com.project.banksystemapp.mapper;

import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.StoreDto;

public class StoreMapper {

    public StoreDto toStoreDto(Store store) {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setBrand(store.getBrand());
        storeDto.setStoreAdmin(UserMapper.toUserDto(store.getStoreAdmin()));
        storeDto.setContact(store.getStoreContact());
        storeDto.setDescription(store.getDescription());
        storeDto.setStoreType(store.getStoreType());
        storeDto.setCreatedAt(store.getCreatedAt());
        storeDto.setUpdatedAt(store.getUpdatedAt());
        return storeDto;
    }

    public Store toEntity(StoreDto storeDto, User storeAdmin) {
        Store store = new Store();
        store.setId(storeDto.getId());
        store.setBrand(storeDto.getBrand());
        store.setStoreAdmin(storeAdmin);
        store.setStoreContact(storeDto.getContact());
        store.setDescription(storeDto.getDescription());
        store.setStoreType(storeDto.getStoreType());
        store.setCreatedAt(storeDto.getCreatedAt());
        store.setUpdatedAt(storeDto.getUpdatedAt());
        return store;
    }


}
