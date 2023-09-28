package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.CategoryForListingDto;
import com.turkcell.spring.starter.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(CategoryForListingDto var1) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, CategoryForListingDto category) {

    }

    @Override
    public List<CategoryForListingDto> getAll() {
        // DTO => Data Transfer Object
        return categoryRepository.getForListing();
    }

    @Override
    public Category getById(int id) {
        return null;
    }
}
