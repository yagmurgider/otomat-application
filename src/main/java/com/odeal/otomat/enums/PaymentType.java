package com.odeal.otomat.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum PaymentType {
    CREDIT_CARD(new PaymentTypeDetail[]{PaymentTypeDetail.CONTACTLESS, PaymentTypeDetail.DIRECT}),
    CASH(new PaymentTypeDetail[]{PaymentTypeDetail.CHANGE, PaymentTypeDetail.BANKNOTE});

    private PaymentTypeDetail[] paymentTypeDetails;
}


