package com.odeal.otomat.dto;

import com.odeal.otomat.abstacts.AbstractGenericType;
import com.odeal.otomat.enums.PaymentType;
import com.odeal.otomat.enums.PaymentTypeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoDTO extends AbstractGenericType {

    private Long id;

    private ProductDTO product;

    private int quantity;

    private int sugarQuantity;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentTypeDetail paymentTypeDetail;

    private Double totalAmount;

    private Double remainingAmount;

    private Double receiveAmount;
}
