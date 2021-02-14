package com.odeal.otomat.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ProductType {
    FOOD,
    DRINK(new ProductTypeDetail[] {ProductTypeDetail.HOT, ProductTypeDetail.COLD});

    private ProductTypeDetail[] productTypeDetails;

}
