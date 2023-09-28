package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Sales;

import java.util.List;

public interface SalesService {
    void add(Sales sales);

    void delete(int id);

    void update(int id, Sales sales);
    List<Sales> getAll();
    Sales getById(int id);
}
