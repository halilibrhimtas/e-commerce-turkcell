package com.turkcell.spring.starter.entities.dtos.order;

import com.turkcell.spring.starter.entities.OrderDetails;
import com.turkcell.spring.starter.entities.dtos.orderDetails.OrderDetailsForAddDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderForAddDto {

    @NotBlank(message = "Sipariş tarihi girmelisiniz.")
    @Size(min = 10, max = 10)
    private String orderDate;
    private String requiredDate;
    private List<OrderDetailsForAddDto> orderDetailsList;
    @Positive
    private double freight;
    private String shipCity;
    private String shipCountry;
}
