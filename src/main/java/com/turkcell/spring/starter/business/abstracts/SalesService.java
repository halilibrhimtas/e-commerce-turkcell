package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Sales;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForAddDto;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesService {
    void add(SalesForAddDto salesForAddDto);

    void delete(int id);

    void update(int id, SalesForUpdateDto salesForUpdateDto);
    List<Sales> getAll();
    Sales getById(int id);
}
