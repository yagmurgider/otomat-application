package com.odeal.otomat.entity;

import com.odeal.otomat.abstacts.AbstractEntity;
import com.odeal.otomat.enums.ProductType;
import com.odeal.otomat.enums.ProductTypeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntity {

    private String name;

    private int slotNumber;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    private ProductTypeDetail productTypeDetail;

}
