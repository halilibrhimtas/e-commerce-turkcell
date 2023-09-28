package com.turkcell.spring.starter.controllers;

import com.turkcell.spring.starter.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForDetailDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.starter.entities.dtos.product.ProductForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.turkcell.spring.starter.business.abstracts.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {

    private final ProductService productService;
    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductForListingDto> getProductsForListing()
    {
        List<ProductForListingDto> productsInDb = productService.getAll();
        return productsInDb;
    }

    @GetMapping("getByProductId")
    public ProductForDetailDto getProductForDetail(@RequestParam("id") int id)
    {
        ProductForDetailDto productForDetailDto = productService.getById(id);
        return productForDetailDto;
    }

    @PostMapping("add")
    public ResponseEntity<String> addProductDto(@RequestBody @Valid ProductForAddDto productForAddDtoDto) {
        productService.add(productForAddDtoDto);
        return ResponseEntity.ok("Ürün başarıyla eklendi.");
    }

    @PostMapping("update")
    public ResponseEntity<String> updateProductDto(@RequestParam("id") int id, @RequestBody @Valid ProductForUpdateDto productForUpdateDto) {
        productService.update(id, productForUpdateDto);
        return ResponseEntity.ok("Ürün başarıyla güncellendi.");
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteProductDto(@RequestParam("id") int id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok("Ürün başarıyla silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ürün silinirken bir hata oluştu.");
        }
    }
}
