package com.project.banksystemapp.service.impl;

import com.project.banksystemapp.domain.PaymentType;
import com.project.banksystemapp.exceptions.UserException;
import com.project.banksystemapp.mapper.ShiftReportMapper;
import com.project.banksystemapp.modal.*;
import com.project.banksystemapp.payload.dto.ShiftReportDto;
import com.project.banksystemapp.repository.OrderRepository;
import com.project.banksystemapp.repository.RefundRepository;
import com.project.banksystemapp.repository.ShiftReportRepository;
import com.project.banksystemapp.repository.UserRepository;
import com.project.banksystemapp.service.ShiftReportService;
import com.project.banksystemapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public ShiftReportDto startShift() throws UserException {
        User user = userService.getCurrentUser();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.withHour(0).withMinute(0).withSecond(0);

        LocalDateTime endOfDay = now.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(
                user, startOfDay, endOfDay
        );

        if(existing.isPresent()) {
            throw new UserException("Shift already exists");
        }

        Branch branch = user.getBranch();

        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(user)
                .shiftStart(now)
                .branch(branch)
                .build();

        return ShiftReportMapper.toDto(shiftReportRepository.save(shiftReport));
    }

    @Override
    public ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws UserException {
        User user = userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user)
                .orElseThrow(() -> new UserException("Shift not found"));

        shiftReport.setShiftEnd(shiftEnd);

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween
                (user.getId(), shiftReport.getShiftStart(), shiftReport.getShiftEnd());

        List<Order> orders= orderRepository.findByCashierIdAndCreatedAtBetween
                (user.getId(), shiftReport.getShiftStart(), shiftReport.getShiftEnd());

        double totalRefunds = refunds.stream()
                .mapToDouble( refund -> refund.getAmount()!=null? refund.getAmount():0.0).sum();

        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        int totalOrders = orders.size();
        double netSale = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSale(netSale);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        return ShiftReportMapper.toDto(shiftReportRepository.save(shiftReport));
    }

    @Override
    public ShiftReportDto getShiftReportById(Long id) {
        return ShiftReportMapper.toDto(shiftReportRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Shift not found with id: " + id)
                ));
    }

    @Override
    public List<ShiftReportDto> getAllShiftReports() {
        return shiftReportRepository.findAll()
                .stream()
                .map(ShiftReportMapper::toDto)
                .toList();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByBranchId(Long branchId) {
        return shiftReportRepository.findByBranchId(branchId)
                .stream()
                .map(ShiftReportMapper::toDto)
                .toList();
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId) {
        return shiftReportRepository.findByCashierId(cashierId)
                .stream()
                .map(ShiftReportMapper::toDto)
                .toList();
    }

    @Override
    public ShiftReportDto getCurrentShiftProgress(Long cashierId) throws UserException {
        User user = userService.getCurrentUser();

        ShiftReport shiftReport = shiftReportRepository
                .findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user)
                .orElseThrow(() -> new UserException("User not found with id: " + cashierId));

        LocalDateTime now = LocalDateTime.now();

        List<Order> orders = orderRepository.findByCashierIdAndCreatedAtBetween(
                user.getId(), shiftReport.getShiftStart(), now
        );

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween
                (user.getId(), shiftReport.getShiftStart(), shiftReport.getShiftEnd());


        double totalRefunds = refunds.stream()
                .mapToDouble( refund -> refund.getAmount()!=null? refund.getAmount():0.0).sum();

        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        int totalOrders = orders.size();
        double netSale = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSale(netSale);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        return ShiftReportMapper.toDto(shiftReportRepository.save(shiftReport));
    }

    @Override
    public ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws UserException {
        User  cashier = userRepository.findById(cashierId).orElseThrow(
                () -> new UserException("User not found with id: " + cashierId)
        );

        LocalDateTime start =date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end =date.withHour(23).withMinute(59).withSecond(59);

        ShiftReport report = shiftReportRepository.findByCashierAndShiftStartBetween(
                cashier, start, end
        ).orElseThrow(
                () -> new UserException("Shift not found for cashier with id: " + cashierId)
        );


        return ShiftReportMapper.toDto(report);
    }


    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {

        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getPaymentType()!=null?
                        order.getPaymentType():PaymentType.CASH));

        List<PaymentSummary> paymentSummaries = new ArrayList<>();
        for(Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()) {
            double totalAmount = entry.getValue().stream()
                    .mapToDouble(Order::getTotalAmount).sum();
            int transactions = entry.getValue().size();
            double percentage = (totalAmount/totalSales)*100;

            PaymentSummary ps = new PaymentSummary();
            ps.setPaymentType(entry.getKey());
            ps.setTotalAmount(totalAmount);
            ps.setTransactionCount(transactions);
            ps.setPercentage(percentage);

            paymentSummaries.add(ps);
        }

        return paymentSummaries;
    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product, Integer> productSalesMap = new HashMap<>();

        for(Order order : orders) {
            for(OrderItem orderItem : order.getItems()) {
                Product product = orderItem.getProduct();
                productSalesMap.put(product,
                        productSalesMap.getOrDefault(product, 0)+orderItem.getQuantity());
            }
        }

        return productSalesMap.entrySet().stream()
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .toList();
    }
}
