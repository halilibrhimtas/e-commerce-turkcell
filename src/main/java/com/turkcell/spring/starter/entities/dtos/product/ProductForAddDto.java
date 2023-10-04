package com.turkcell.spring.starter.entities.dtos.product;

import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.Supplier;
import jakarta.validation.constraints.*;
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

    @NotEmpty(message = "Unit Price değeri boş olamaz")
    @Min(0)
    private double unitPrice;

    @Min(0)
    private int unitsInStock;

    private int unitsOnOrder;

    @Min(1)
    @NotNull(message = "Supplier ID değeri boş olamaz")
    private int supplierId;

    @Min(1)
    @NotEmpty(message = "Category ID değeri boş olamaz")
    private int categoryId;
}
