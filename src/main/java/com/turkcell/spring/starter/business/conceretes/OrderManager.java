package com.turkcell.spring.starter.business.conceretes;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.OrderDetails;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.entities.dtos.orderDetails.OrderDetailsForAddDto;
import com.turkcell.spring.starter.repository.OrderDetailsRepository;
import com.turkcell.spring.starter.repository.OrderRepository;
import com.turkcell.spring.starter.entities.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderManager(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public void add(OrderForAddDto orderForAddDto) {
        requiredDateMustBeAfterOrderDate(orderForAddDto.getOrderDate(), orderForAddDto.getRequiredDate());
        shipCityMustNotBeEmpty(orderForAddDto.getShipCity());
        Order order = new Order();
        order.setFreight(orderForAddDto.getFreight());
        order.setOrderDate(orderForAddDto.getOrderDate());
        order.setShipCity(orderForAddDto.getShipCity());
        order.setRequiredDate(orderForAddDto.getRequiredDate());
        Order order1 = orderRepository.save(order);
        int newOrderId = order1.getOrderId();
        List<OrderDetailsForAddDto> orderDetailsList = orderForAddDto.getOrderDetailsList();

        System.out.println(order1);

        for(OrderDetailsForAddDto orderDetailsForAddDto : orderDetailsList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(newOrderId);
            orderDetails.setProductId(orderDetailsForAddDto.getProductId());
            orderDetails.setUnitPrice(orderDetailsForAddDto.getUnitPrice());
            orderDetails.setDiscount(orderDetailsForAddDto.getDiscount());
            orderDetails.setQuantity(orderDetailsForAddDto.getQuantity());

            orderDetailsRepository.save(orderDetails);

        }


    }

    @Override
    public void delete(int id) {

        deleteOrderMustExist(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void update(int id, OrderForUpdateDto orderForUpdateDto) {
        orderRepository.updateOrder(id, orderForUpdateDto.getFreight(), orderForUpdateDto.getShipCity(), orderForUpdateDto.getShipCountry());
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findByOrderId(id);
    }

    private void requiredDateMustBeAfterOrderDate(String orderDate, String requiredDate) {
        LocalDate parsedOrderDate = LocalDate.parse(orderDate);
        LocalDate parsedRequiredDate = LocalDate.parse(requiredDate);
        if (parsedRequiredDate.isBefore(parsedOrderDate)) {
            throw new BusinessException("Gereken tarih, sipariş tarihinden önce olamaz.");
        }
    }

    private void shipCityMustNotBeEmpty(String shipCity) {
        if (shipCity == null || shipCity.trim().isEmpty()) {
            throw new BusinessException("Gemi şehir alanı boş olamaz.");
        }
    }

    private void deleteOrderMustExist(int orderId) {
        Order existingOrder = orderRepository.findByOrderId(orderId);
        if (existingOrder == null) {
            throw new BusinessException("Silinecek sipariş bulunamadı.");
        }
    }



}
