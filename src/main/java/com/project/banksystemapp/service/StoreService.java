package com.project.banksystemapp.service;


import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id);
    List<StoreDto> getAllStores();
    Store getStoreByAdmin();
    StoreDto updateStore(Long id, StoreDto storeDto);
    StoreDto deleteStore(Long id);
    StoreDto getStoreByEmployee();
}
