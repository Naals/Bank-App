package com.project.banksystemapp.service;

import com.project.banksystemapp.domain.OrderStatus;
import com.project.banksystemapp.domain.PaymentType;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) throws UserException;
    OrderDto getOrderById(Long id);
    List<OrderDto> getOrdersByBranch(Long branchId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status
    );
    List<OrderDto> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id);
    List<OrderDto> getTodayOrdersByBranch(Long branchId);
    List<OrderDto> getOrderByCustomerId(Long cashierId);
    List<OrderDto> getTop5RecentOrderByBranch(Long branchId);

}
