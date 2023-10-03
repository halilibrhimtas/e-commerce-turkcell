package com.turkcell.spring.starter.controllers;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<Order> get(){
        return orderService.getAll();
    }

    @GetMapping("getById")
    public Order getById(@RequestParam("id") int id){
        return orderService.getById(id);
    }

    @PostMapping("add")
    public void add(@RequestBody @Valid OrderForAddDto order){
        orderService.add(order);
    }

    @PostMapping("delete")
    public void delete(@RequestParam("id") int id){
        orderService.delete(id);
    }

    @PostMapping("update")
    public void update(@RequestParam("id") int id, @RequestBody OrderForUpdateDto orderForUpdateDto){
        orderService.update(id, orderForUpdateDto);
    }

}
