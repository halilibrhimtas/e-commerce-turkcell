package com.turkcell.spring.starter.entities.dtos.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductForAddDto {

    @NotBlank(message = "Ürün adı girmek zorunludur.")
    @Size(min = 3)
    private String productName;

    @NotBlank(message = "Ürün sayısı girmek zorunludur.")
    @Min(10)
    private String quantityPerUnit;

    //@NotBlank(message = "Ürün fiyatı girmek zorunludur.")
    private double unitPrice;

    @Min(1)
    private int unitsInStock;
    private int unitsOnOrder;
}
