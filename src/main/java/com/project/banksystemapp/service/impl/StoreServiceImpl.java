package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.StoreMapper;
import com.project.banksystemapp.modal.Store;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.StoreDto;
import com.project.banksystemapp.repository.StoreRepository;
import com.project.banksystemapp.service.StoreService;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        Store savedStore = storeRepository.save(store);
        return StoreMapper.toStoreDto(savedStore);
    }

    @Override
    public StoreDto getStoreById(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
        return StoreMapper.toStoreDto(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toStoreDto)
                .toList();
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User currentUser = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(currentUser.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws UserException {
        User currentUser = userService.getCurrentUser();

        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        if (store==null) {
            throw new RuntimeException("Access denied: you are not the owner of this store");
        }

        Store updatedStore = StoreMapper.updateEntity(store, storeDto);
        Store savedStore = storeRepository.save(updatedStore);

        return StoreMapper.toStoreDto(savedStore);
    }

    @Override
    public void deleteStore() throws UserException {
        Store store = getStoreByAdmin();

        storeRepository.delete(store);
    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();

        if(currentUser==null){
            throw new UserException("You are not logged in");
        }

        return StoreMapper.toStoreDto(currentUser.getStore());
    }
}

