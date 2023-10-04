package com.turkcell.spring.starter.entities.dtos.orderDetails;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsForAddDto {

    @Column(name = "product_id")
    private int productId;

    @Min(1)
    @Column(name = "quantity")
    private int quantity;

}
