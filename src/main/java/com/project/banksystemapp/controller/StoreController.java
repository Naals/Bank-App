package com.project.banksystemapp.controller;

import com.project.banksystemapp.domain.StoreStatus;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.StoreMapper;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.StoreDto;
import com.project.banksystemapp.payload.response.ApiResponse;
import com.project.banksystemapp.service.StoreService;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StoreDto> createStore(
            @RequestBody StoreDto storeDto,
            @RequestHeader("Authorization") String jwt
    ) throws UserException {

        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));
    }

    @GetMapping("{id}")
    public ResponseEntity<StoreDto> getStoreById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) {

        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStores(
            @RequestHeader("Authorization") String jwt
    ) {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {

        return ResponseEntity.ok(StoreMapper.toStoreDto(storeService.getStoreByAdmin()));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreEmployee(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @PathVariable Long id,
            @RequestBody StoreDto storeDto,
            @RequestHeader("Authorization") String jwt
    ) throws UserException, AccessDeniedException {

        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(
            @PathVariable Long id,
            @RequestBody StoreStatus storeStatus,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        return ResponseEntity.ok(storeService.moderateStore(id, storeStatus));
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteStore(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        storeService.deleteStore();
        ApiResponse api = new ApiResponse();
        api.setMessage("Store deleted successfully");
        return ResponseEntity.ok(api);
    }

}
