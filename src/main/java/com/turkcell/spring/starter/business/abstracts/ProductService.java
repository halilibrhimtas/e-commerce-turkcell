package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.dtos.product.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void add(ProductForAddDto product);

    void delete(int id);

    void update(int id, ProductForUpdateDto product);
    List<ProductForListingDto> getAll();
    ProductForDetailDto getById(int id);
}
