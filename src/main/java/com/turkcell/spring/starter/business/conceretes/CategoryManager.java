package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.CategoryForAddDto;
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
    public void add(CategoryForAddDto request) {
        categoryWithSameNameShouldNotExist(request.getCategoryName());
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);
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

    private void categoryWithSameNameShouldNotExist(String categoryName){
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if(categoryWithSameName != null){
            throw new BusinessException("Aynı kategori isminden 2 kategori bulunamaz.");
        }
    }
}
