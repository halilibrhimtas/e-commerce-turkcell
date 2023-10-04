package com.turkcell.spring.starter.repository;

import java.util.List;

import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.ProductForDetailDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);
    Product findByProductId(int productId);

    @Query(value = "SELECT new com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto(p.productId, p.productName, p.quantityPerUnit, p.unitPrice, p.unitsInStock, p.unitsOnOrder, p.discontinued) FROM Product p")
    List<ProductForListingDto> getProductForListing();

    @Query(value = "Select new com.turkcell.spring.starter.entities.dtos.product.ProductForDetailDto(p.productId, p.productName, p.quantityPerUnit, p.unitPrice, p.unitsInStock, p.unitsOnOrder, p.reorderLevel) from Product p WHERE p.productId = :productId")
    ProductForDetailDto getProductForDetailDto(@Param("productId") int productId);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.productName = :productName, p.quantityPerUnit = :quantityPerUnit, p.unitPrice = :unitPrice, p.unitsInStock = :unitsInStock, p.unitsOnOrder = :unitsOnOrder WHERE p.productId = :productId")
    void updateProductDto(@Param("productId") int productId, @Param("productName") String productName, @Param("quantityPerUnit") String quantityPerUnit, @Param("unitPrice") double unitPrice, @Param("unitsInStock") int unitsInStock, @Param("unitsOnOrder") int unitsOnOrder);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.unitsInStock = :unitsInStock WHERE p.productId = :productId")
    void updateUnitsInStock(@Param("productId") int productId, @Param("unitsInStock") int unitsInStock);
}

