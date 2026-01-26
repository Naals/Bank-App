package com.project.banksystemapp.controller;

import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.modal.User;
import com.project.banksystemapp.payload.dto.ProductDto;
import com.project.banksystemapp.payload.response.ApiResponse;
import com.project.banksystemapp.service.ProductService;
import com.project.banksystemapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            @RequestHeader("Authorization") String jwt
    ) throws UserException, Exception {
        User currentUser = userService.getUserFromJwtToken(jwt);
        ProductDto createdProduct = productService.createProduct(productDto, currentUser);
        return ResponseEntity.ok(createdProduct);
    }

    // READ — все продукты магазина пользователя
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") String jwt

    ) {
        List<ProductDto> products = productService.getAllProductsByStoredId(storeId);
        return ResponseEntity.ok(products);
    }

    // READ — один продукт по ID
    @GetMapping("/{storeId}")
    public ResponseEntity<List<ProductDto>> getProductByStoreId(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") String jwt
    ) {
        return ResponseEntity.ok(productService.getAllProductsByStoredId(storeId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto,
            @RequestHeader("Authorization") String jwt
    ) throws Exception, UserException {
        User currentUser = userService.getUserFromJwtToken(jwt);
        ProductDto updatedProduct = productService.updateProduct(id, productDto, currentUser);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User currentUser = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(id, currentUser);

        ApiResponse api = new ApiResponse();
        api.setMessage("Product deleted successfully");
        return ResponseEntity.ok(api);
    }

    // SEARCH
    @GetMapping("/store/{storeId}/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @PathVariable Long storeId,
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt
    ) {
        List<ProductDto> products = productService.searchByKeyword(storeId, keyword);
        return ResponseEntity.ok(products);
    }
}

