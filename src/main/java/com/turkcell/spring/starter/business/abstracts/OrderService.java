package com.turkcell.spring.starter.business.abstracts;
import com.turkcell.spring.starter.entities.Order;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void add(OrderForAddDto orderForAddDto);

    void delete(int id);

    void update(int id, OrderForUpdateDto orderForUpdateDto);
    List<Order> getAll();
    Order getById(int id);
}
