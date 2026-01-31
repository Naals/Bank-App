package com.project.banksystemapp.controller;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.BranchDto;
import com.project.banksystemapp.payload.response.ApiResponse;
import com.project.banksystemapp.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;


    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws UserException {
        BranchDto createBranch = branchService.createBranch(branchDto);

        return ResponseEntity.ok().body(createBranch);
    }

    @GetMapping("{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) {
        BranchDto branchById = branchService.getBranchById(id);

        return ResponseEntity.ok().body(branchById);
    }

    @GetMapping("store/{storeId}")
    public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(
            @PathVariable Long storeId
    ) {
        List<BranchDto> branchById = branchService.getAllBranchesByStoreId(storeId);

        return ResponseEntity.ok().body(branchById);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteBranchById(
            @PathVariable Long id
    ) {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch deleted successfully");

        return ResponseEntity.ok().body(apiResponse);
    }



}
