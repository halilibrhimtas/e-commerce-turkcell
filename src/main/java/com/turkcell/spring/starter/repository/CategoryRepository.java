package com.turkcell.spring.starter.repository;

import java.util.List;
import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByCategoryNameStartingWith(String categoryName);
    Category findByCategoryName(String categoryName);
    Category findByCategoryId(int categoryId);
    List<Category> findByCategoryNameContainingIgnoreCase(String categoryName);


    // Native SQL
    // JPQL => JPA'nın SQL'e neredeyse birebir benzer versiyonu..
    // JPQL => Tablo ismi yerine entity yazmak

    @Query("SELECT c FROM Category c WHERE LOWER(c.description) LIKE %:description%")
    List<Category> searchDescriptionWithJPQL(String description);

    @Query("SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE %:categoryName% AND LENGTH(c.categoryName) < 8")
    List<Category> sortCategoryNameLengthWithJPQL(String categoryName);

    @Query("SELECT c FROM Category c WHERE c.id >= :id")
    List<Category> sortByIdWithJPQL(int id);


    @Query(value = "Select * from categories Where category_name LIKE %:categoryName%", nativeQuery = true)
    List<Category> searchNative(String categoryName);

    @Query(value = "SELECT COUNT(*) as TOPLAM_ÜRÜN_SAYISI FROM products WHERE category_id = %:categoryId%", nativeQuery = true)
    Integer getProductCounts(int categoryId);

    @Query(value = "SELECT COUNT(*) FROM categories", nativeQuery = true)
    Integer countCategories();

    @Query(value = "SELECT new com.turkcell.spring.starter.entities.dtos.category.CategoryForListingDto(c.categoryId, c.categoryName) FROM Category c")
    List<CategoryForListingDto> getForListing();

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.categoryName = :categoryName, c.description = :description WHERE c.categoryId = :categoryId")
    void updateCategoryDto(@Param("categoryId") int categoryId, @Param("categoryName") String categoryName, @Param("description") String description);


}

