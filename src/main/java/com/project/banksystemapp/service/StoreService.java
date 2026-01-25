package com.project.banksystemapp.service;


import com.project.banksystemapp.domain.StoreStatus;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id);
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws UserException;
    void deleteStore() throws UserException;
    StoreDto getStoreByEmployee() throws UserException;
    StoreDto moderateStore(Long id, StoreStatus storeStatus) throws Exception;
}
