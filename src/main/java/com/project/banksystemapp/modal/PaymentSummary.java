package com.project.banksystemapp.modal;

import com.project.banksystemapp.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {

    private PaymentType paymentType;
    private Double totalAmount;
    private int transactionCount;
    private double percentage;
}
