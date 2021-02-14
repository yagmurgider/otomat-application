package com.odeal.otomat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfo {
    private String productName;
    private int quantity;
    private Double amount;
    private String paymentType;
    private String paymetTypeDetail;
    private Double totalAmount;
    private Double remainingAmount;
    private Double receivedAmount;
}
