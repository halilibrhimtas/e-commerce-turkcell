package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.CategoryForListingDto;

import java.util.List;

public interface CategoryService {

    //Servisler entity'leri değil DTO'ları kullanmalı.
    void add(CategoryForAddDto var1);

    void delete(int id);

    void update(int id, CategoryForListingDto categoryForListingDto);
    List<CategoryForListingDto> getAll();
    Category getById(int id);
}

