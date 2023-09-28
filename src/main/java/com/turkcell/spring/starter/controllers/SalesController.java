package com.turkcell.spring.starter.controllers;
import com.turkcell.spring.starter.business.abstracts.SalesService;
import com.turkcell.spring.starter.entities.Sales;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForAddDto;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SalesController {
    private final SalesService salesService;
    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping()
    public List<Sales> get(){
        return salesService.getAll();
    }

    @GetMapping("getById")
    public Sales getById(@RequestParam("id") int id){
        return salesService.getById(id);
    }

    @PostMapping("add")
    public void add(@RequestBody @Valid SalesForAddDto sales){
        salesService.add(sales);
    }

    @PostMapping("delete")
    public void delete(@RequestParam("id") int id){
        salesService.delete(id);
    }

    @PostMapping("update")
    public void update(@RequestParam("id") int id, @RequestBody SalesForUpdateDto salesForUpdateDto){
        salesService.update(id, salesForUpdateDto);
    }

}
