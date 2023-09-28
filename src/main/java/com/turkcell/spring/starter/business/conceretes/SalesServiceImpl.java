package com.turkcell.spring.starter.business.conceretes;
import com.turkcell.spring.starter.business.abstracts.SalesService;
import com.turkcell.spring.starter.repository.SalesDal;
import com.turkcell.spring.starter.entities.Sales;

import java.util.List;

public class SalesServiceImpl implements SalesService {
    SalesDal salesDal;

    public SalesServiceImpl(SalesDal salesDal) {
        this.salesDal = salesDal;
    }


    @Override
    public void add(Sales sales) {
        salesDal.add(sales);
    }

    @Override
    public void delete(int id) {
        salesDal.delete(id);
    }

    @Override
    public void update(int id, Sales sales) {
        salesDal.update(id,sales);
    }

    @Override
    public List<Sales> getAll() {
        return salesDal.getAll();
    }

    @Override
    public Sales getById(int id) {
        return salesDal.getById(id);
    }
}
