package com.odeal.otomat.dto;

import com.odeal.otomat.abstacts.AbstractGenericType;
import com.odeal.otomat.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends AbstractGenericType {

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private Double amount;
}