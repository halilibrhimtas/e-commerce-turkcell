package com.turkcell.spring.starter.entities.dtos.category;

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
public class CategoryForUpdateDto {
    @NotBlank(message = "Kategori adı girmek zorunludur.")
    @Size(max = 20, min = 3)
    private String categoryName;

    @NotBlank(message = "Açıklama alanı zorunludur.")
    @Size(max = 500)
    private String description;
}
