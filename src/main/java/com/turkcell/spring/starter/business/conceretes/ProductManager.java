package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.ProductService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.*;
import com.turkcell.spring.starter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final MessageSource messageSource;


    @Override
    public void add(ProductForAddDto productForAddDto) {
        addProductMustHaveUniqueName(productForAddDto.getProductName());

        /*
        Product product = new Product();
        product.setProductName(productForAddDto.getProductName());
        product.setQuantityPerUnit(productForAddDto.getQuantityPerUnit());
        product.setUnitPrice(productForAddDto.getUnitPrice());
        product.setUnitsInStock(productForAddDto.getUnitsInStock());
        product.setUnitsOnOrder(productForAddDto.getUnitsOnOrder());
        product.setDiscontinued(0);
        */

        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);

        Product product1 = modelMapper.map(productForAddDto, Product.class);

        productRepository.save(product1);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }
    @Override
    public void update(int id, ProductForUpdateDto productForUpdateDto) {
        updateProductMustExist(id);
        updateProductPriceMustBeNonNegative(productForUpdateDto.getUnitPrice());

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

    private void addProductMustHaveUniqueName(String newProductName) {
        Product existingProduct = productRepository.findByProductName(newProductName);
        if (existingProduct != null) {
            throw new BusinessException(messageSource.getMessage("addProductMustHaveUniqueName", new Object[] {newProductName}, LocaleContextHolder.getLocale()));
        }
    }
    private void updateProductMustExist(int productId) {
        Product existingProduct = productRepository.findByProductId(productId);
        if (existingProduct == null) {
            throw new BusinessException(messageSource.getMessage("updateProductMustExist", new Object[] {productId}, LocaleContextHolder.getLocale()));
        }
    }
    private void updateProductPriceMustBeNonNegative(double newUnitPrice) {
        if (newUnitPrice < 0) {
            throw new BusinessException(messageSource.getMessage("updateProductPriceMustBeNonNegative", new Object[] {newUnitPrice}, LocaleContextHolder.getLocale()));
        }
    }
}
