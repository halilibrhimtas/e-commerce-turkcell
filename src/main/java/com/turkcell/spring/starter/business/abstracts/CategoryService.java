package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;

import java.util.List;

public interface CategoryService {

    //Servisler entity'leri değil DTO'ları kullanmalı.
    void add(CategoryForAddDto var1);

    void delete(int id);

    void update(int id, CategoryForUpdateDto categoryForUpdateDto);
    List<CategoryForListingDto> getAll();
    List<Category> getById(int id);
}

