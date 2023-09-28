package com.turkcell.spring.starter.repository;


import com.turkcell.spring.starter.entities.Sales;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class InMemorySalesDal implements SalesDal {

    List<Sales> salesList = new ArrayList();


    @Override
    public void add(Sales sales) {
        salesList.add(sales);
    }

    @Override
    public List<Sales> getAll() {
        return this.salesList;
    }

    @Override
    public ResponseEntity<?> update(int id, Sales sales) {

        for (int i = 0; i < salesList.size(); i++){
            if(sales.getId() == id){
                salesList.set(i,sales);
                return ResponseEntity.ok("Satış başarıyla güncellendi");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> delete(int id) {

        for (Sales sales : salesList){
            if(sales.getId() == id){
                salesList.remove(sales);
                return ResponseEntity.ok("Satış başarıyla silindi");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public Sales getById(int id) {

        for (Sales sales : salesList){
            if(sales.getId() == id){
                return sales;
            }
        }
        return null;
    }
}
