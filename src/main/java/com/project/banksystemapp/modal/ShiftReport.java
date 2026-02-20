package com.project.banksystemapp.modal;

import com.razorpay.Payment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    private Double totalSales;
    private Double totalRefunds;
    private Double netSale;
    private Double totalOrders;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Branch branch;

    @Transient
    private List<PaymentSummary> paymentSummaries;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> topSellingProducts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> recentOrders;

    @OneToMany(mappedBy = "shiftReport", cascade = CascadeType.ALL)
    private List<Refund> refunds;
}
