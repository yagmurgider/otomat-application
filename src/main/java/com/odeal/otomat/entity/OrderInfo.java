package com.odeal.otomat.entity;

import com.odeal.otomat.abstacts.AbstractEntity;
import com.odeal.otomat.enums.PaymentType;
import com.odeal.otomat.enums.PaymentTypeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

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
