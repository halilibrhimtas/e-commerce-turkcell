package com.turkcell.spring.starter.entities.dtos.order;

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
public class OrderForUpdateDto {

    @Negative
    private double freight;

    @NotBlank(message = "Şehir ismi girmek zorunludur.")
    @Size(min = 3)
    private String shipCity;

    private String shipCountry;
}
