package com.turkcell.spring.starter.repository;

import java.util.List;

import com.turkcell.spring.starter.entities.Sales;
import org.springframework.http.ResponseEntity;


public interface SalesDal {
    void add(Sales sales);

    List<Sales> getAll();

    ResponseEntity<?> update(int id, Sales sales);

    ResponseEntity<?> delete(int id);

    Sales getById(int id);
}