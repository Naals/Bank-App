package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.OrderStatus;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.OrderMapper;
import com.project.banksystemapp.modal.*;
import com.project.banksystemapp.payload.dto.OrderDto;
import com.project.banksystemapp.repository.OrderRepository;
import com.project.banksystemapp.repository.ProductRepository;
import com.project.banksystemapp.service.OrderService;
import com.project.banksystemapp.domain.PaymentType;
import com.project.banksystemapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailabilityBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws UserException {

        User cashier = userService.getCurrentUser();

        Branch branch = cashier.getBranch();
        if(branch == null){
            throw new UserException("Cashiers branch not found");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .totalAmount(orderDto.getTotalAmount())
                .paymentType(orderDto.getPaymentType())
                        .build();

        List<OrderItem> orderItems  = orderDto.getItems().stream().map(
                orderItemDto -> {
                    Product product = productRepository.findById(orderItemDto.getProductId()).orElseThrow(
                            () -> new EntityNotFoundException("Product not found")
                    );

                    return OrderItem.builder()
                            .product(product)
                            .quantity(orderItemDto.getQuantity())
                            .price(product.getSellingPrice() * orderItemDto.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();

        Double total = orderItems.stream().mapToDouble(
                OrderItem::getPrice
        ).sum();

        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id).map(OrderMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Order not found with id: " + id)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(
            Long branchId, Long customerId,Long cashierId, PaymentType paymentType, OrderStatus status) {
        return orderRepository.findByBranchId(branchId)
                .stream()
                .filter(order -> customerId == null ||
                        (order.getCustomer() != null &&
                                order.getCustomer().getId().equals(customerId)
                        )
                ).filter(order -> cashierId == null ||
                        (order.getCashier() != null &&
                                order.getCashier().getId().equals(cashierId)
                        )
                ).filter(order -> paymentType == null ||
                        (order.getPaymentType() == (paymentType)
                        )
                ).map(OrderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId)
                .stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order not found with id: " + id)
        );
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(
                branchId, start, end
        ).stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getOrderByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream().map(OrderMapper::toDto).toList();
    }

    @Override
    public List<OrderDto> getTop5RecentOrderByBranch(Long branchId) {
        return orderRepository.findTop5byBranchIdOrderByCreatedAtDesc(branchId)
                .stream().map(OrderMapper::toDto).toList();
    }
}
