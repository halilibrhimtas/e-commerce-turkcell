package com.turkcell.spring.starter.controllers;
import com.turkcell.spring.starter.business.abstracts.SalesService;
import com.turkcell.spring.starter.business.conceretes.SalesManager;
import com.turkcell.spring.starter.entities.Sales;
import com.turkcell.spring.starter.repository.InMemorySalesDal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {
    SalesService salesService = new SalesManager(new InMemorySalesDal());

    @GetMapping("get")
    public List<Sales> get(){
        return salesService.getAll();
    }

    @GetMapping("getById")
    public Sales getById(@RequestParam("id") int id){
        return salesService.getById(id);
    }

    @PostMapping("add")
    public void add(@RequestBody Sales sales){
        salesService.add(sales);
    }

    @PostMapping("delete")
    public void delete(@RequestParam("id") int id){
        salesService.delete(id);
    }

    @PostMapping("update")
    public void update(@RequestParam("id") int id, @RequestBody Sales sales){
        salesService.update(id, sales);
    }

}
