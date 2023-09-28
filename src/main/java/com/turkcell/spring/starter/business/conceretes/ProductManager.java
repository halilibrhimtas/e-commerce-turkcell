package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.*;
import com.turkcell.spring.starter.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void add(ProductForAddDto productForAddDto) {
        Product product = new Product();
        product.setProductName(productForAddDto.getProductName());
        product.setQuantityPerUnit(productForAddDto.getQuantityPerUnit());
        product.setUnitPrice(productForAddDto.getUnitPrice());
        product.setUnitsInStock(productForAddDto.getUnitsInStock());
        product.setUnitsOnOrder(productForAddDto.getUnitsOnOrder());
        product.setDiscontinued(0);

        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(int id, ProductForUpdateDto productForUpdateDto) {
        productRepository.updateProductDto(id,
                productForUpdateDto.getProductName(),
                productForUpdateDto.getQuantityPerUnit(),
                productForUpdateDto.getUnitPrice(),
                productForUpdateDto.getUnitsInStock(),
                productForUpdateDto.getUnitsOnOrder()
        );
    }

    @Override
    public List<ProductForListingDto> getAll() {
        return productRepository.getProductForListing();
    }

    @Override
    public ProductForDetailDto getById(int id) {
        return productRepository.getProductForDetailDto(id);
    }
}
