package com.turkcell.spring.starter.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForAddDto {
    private String productName;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
}
