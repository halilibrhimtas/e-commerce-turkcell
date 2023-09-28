package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
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
        categoryNameLengthExceeded(request.getCategoryName(), 20);
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        Category category = categoryRepository.findByCategoryId(id);
        System.out.println(category);
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(int id, CategoryForUpdateDto categoryForUpdateDto) {
        categoryNameMustBeUniqueWhenEditing(categoryForUpdateDto.getCategoryName());
        categoryRepository.updateCategoryDto(id, categoryForUpdateDto.getCategoryName(), categoryForUpdateDto.getDescription());
    }

    @Override
    public List<CategoryForListingDto> getAll() {
        // DTO => Data Transfer Object
        return categoryRepository.getForListing();
    }

    @Override
    public List<Category> getById(int id) {
        return null;
    }

    private void categoryWithSameNameShouldNotExist(String categoryName){
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if(categoryWithSameName != null){
            throw new BusinessException("Aynı kategori isminden 2 kategori bulunamaz.");
        }
    }

    private void categoryProductLimitExceeded(Category category, int maxProductCount) {
        if (category.getProducts().size() >= maxProductCount) {
            throw new BusinessException("Kategoriye ait ürün sayısı maksimum sınırı aştı.");
        }
    }

    private void categoryNameLengthExceeded(String categoryName, int maxLength) {
        if (categoryName.length() > maxLength) {
            throw new BusinessException("Kategori adı en fazla " + maxLength + " karakter uzunluğunda olmalıdır.");
        }
    }
    private void categoryNameMustBeUniqueWhenEditing(String newCategoryName) {
        Category existingCategory = categoryRepository.findByCategoryName(newCategoryName);
        if (existingCategory != null) {
            throw new BusinessException("Bu isimde bir kategori zaten mevcut, kategori adları benzersiz olmalıdır.");
        }
    }


}
