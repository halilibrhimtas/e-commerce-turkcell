package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.business.abstracts.CategoryService;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.CategoryForAddDto;
import com.turkcell.spring.starter.entities.dtos.CategoryForListingDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("categories")
// localhost:8080/categories
public class CategoryController {
    // CategoryService

    // DI
    // Spring IoC => Bağımlılıkların çözümlenmesi..
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryForListingDto> getCategories()
    {
        List<CategoryForListingDto> categoriesInDb = categoryService.getAll();
        return categoriesInDb;
    }

    @GetMapping("getByName")
    public List<Category> getCategoriesByNameAndSort(@RequestParam("name") String name){
       // List<Category> categories = categoryRepository.findByCategoryNameStartingWith(name);
        return null;
    }

    @GetMapping("getByCategoryId")
    public List<Category> getByCategoryId(@RequestParam("id") int id){
        //List<Category> categories = categoryRepository.findByCategoryId(id);
        return null;
    }

    @GetMapping("getByNameIgnoreCase")
    public List<Category> getCategoriesByNameIgnoreCase(@RequestParam("name") String name){
        //List<Category> categories = categoryRepository.findByCategoryNameContainingIgnoreCase(name);
        return null;
    }

    @GetMapping("search")
    public List<Category> search(@RequestParam("name") String name){
        //List<Category> categories = categoryRepository.searchNative(name);
        return null;
    }

    @GetMapping("searchDescription")
    public List<Category> searchDescription(@RequestParam("name") String name){
        //List<Category> categories = categoryRepository.searchDescriptionWithJPQL(name);
        return null;
    }

    @GetMapping("sortCategoryNameLength")
    public List<Category> sortCategoryNameLength(@RequestParam("name") String name){
        //List<Category> categories = categoryRepository.sortCategoryNameLengthWithJPQL(name);
        return null;
    }

    @GetMapping("sortById")
    public List<Category> sortById(@RequestParam("id") int id){
        //List<Category> categories = categoryRepository.sortByIdWithJPQL(id);
        return null;
    }

    @GetMapping("getById")
    public Category getCategoryById(@RequestParam("id") int id){
        //Category category = categoryRepository.findById(id).orElseThrow();
        return null;
    }
    @GetMapping("getProductCounts")
    public Integer getProductCounts(@RequestParam("id") int id){
        //Integer productCounts = categoryRepository.getProductCounts(id);
        return null;
    }

    @GetMapping("getCountCategories")
    public Integer getCountCategories(){
        //Integer countCategories = categoryRepository.countCategories();
        return null;
    }


    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid CategoryForAddDto categoryForAddDto){
        categoryService.add(categoryForAddDto);
       // categoryRepository.save(category);
        return new ResponseEntity("Kategori eklendi", HttpStatus.CREATED);
    }

    // PostgreSQL kurulumu
    // Northwind kurulumu
    // Northwinddeki tüm tabloları modellemek.
    // Araştırma Konusu : Spring JPA'da ilişkisel tabloların modellenmesi.
    // 3 temel tablomuz için veri erişim- business katmanı - controller kodlarının yazılması.

    // 20:05 => Discord Pair
}
