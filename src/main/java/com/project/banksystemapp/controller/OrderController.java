package com.project.banksystemapp.controller;

import com.project.banksystemapp.domain.OrderStatus;
import com.project.banksystemapp.domain.PaymentType;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.OrderDto;
import com.project.banksystemapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws UserException {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderById(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false)OrderStatus orderStatus) {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId,
                customerId, cashierId, paymentType, orderStatus));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCashierId(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }

    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDto>> getTodayOrder(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDto>> getCustomerOrder(
            @PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrders(
            @PathVariable Long branchId) {
        return ResponseEntity.ok(orderService.getTop5RecentOrderByBranch(branchId));
    }

}
