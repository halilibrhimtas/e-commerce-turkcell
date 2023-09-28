package com.turkcell.spring.starter.entities.dtos.sales;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesForAddDto {

    @NotBlank(message = "Sipariş tarihi girmelisiniz.")
    @Size(min = 10, max = 10)
    private String orderDate;
    private String requiredDate;

    @Positive
    private double freight;

    private String shipCity;
    private String shipCountry;
}
