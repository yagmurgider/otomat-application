package com.odeal.otomat.enums;

public enum PaymentType {
    CREDIT_CARD("TEMASSIZ","TEMASLI"),
    CASH("BOZUK","KAĞIT");

    private String value1;
    private String value2;

    PaymentType (String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
}


