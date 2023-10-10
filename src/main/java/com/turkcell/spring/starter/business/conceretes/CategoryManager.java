package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForUpdateDto;
import com.turkcell.spring.starter.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private final MessageSource messageSource;


    @Override
    @Transactional
    public void add(CategoryForAddDto request) {

        categoryWithSameNameShouldNotExist(request.getCategoryName());
        categoryNameLengthExceeded(request.getCategoryName());
        /*
        categoryNameLengthExceeded(request.getCategoryName(), 20);
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());
        */
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);

        Category category1 = modelMapper.map(request, Category.class);
        categoryRepository.save(category1);
    }

    @Override
    public void delete(int id) {
        Category category = categoryRepository.findByCategoryId(id);
        categoryContainsProducts(category);
        System.out.println(category);
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(int id, CategoryForUpdateDto categoryForUpdateDto) {
        categoryWithSameNameShouldNotExist(categoryForUpdateDto.getCategoryName());
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
            throw new BusinessException(messageSource.getMessage("categoryWithSameName", new Object[] {categoryName}, LocaleContextHolder.getLocale()));
        }
    }

    private void categoryContainsProducts(Category category) {
        if (!category.getProducts().isEmpty()) {
            throw new BusinessException(messageSource.getMessage("categoryContainsProducts", new Object[] {category.getCategoryName()}, LocaleContextHolder.getLocale()));
        }
    }

    private void categoryNameLengthExceeded(String categoryName) {
        if (categoryName.length() > 20) {
            throw new BusinessException(messageSource.getMessage("categoryNameLengthExceeded", new Object[] {categoryName}, LocaleContextHolder.getLocale()));
        }
    }
}
