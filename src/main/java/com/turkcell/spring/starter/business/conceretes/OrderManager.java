package com.turkcell.spring.starter.business.conceretes;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.*;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.entities.dtos.orderDetails.OrderDetailsForAddDto;
import com.turkcell.spring.starter.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    private final ProductRepository productRepository;

    public OrderManager(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void add(OrderForAddDto orderForAddDto) {
        //requiredDateMustBeAfterOrderDate(orderForAddDto.getOrderDate(), orderForAddDto.getRequiredDate());
        //shipCityMustNotBeEmpty(orderForAddDto.getShipCity());

        Customer customer = customerRepository.findByCustomerStringId(orderForAddDto.getCustomerId());

        if (customer == null) {
            throw new BusinessException("Eklediğin sipariş için eşleşen customer bulunamadı.");
        }

        Employee employee = employeeRepository.findByEmployeeId(orderForAddDto.getEmployeeId());
        if (employee == null) {
            throw new BusinessException("Eklediğin sipariş için eşleşen employee bulunamadı.");
        }

        LocalDate orderDate = LocalDate.now();

        LocalDate requiredDate = LocalDate.parse(orderForAddDto.getRequiredDate());
        if (requiredDate.isBefore(orderDate)) {
            throw new BusinessException("Required Date, Order Date'den önce olamaz.");
        }

        Order order = new Order();
        order.setFreight(orderForAddDto.getFreight());
        order.setOrderDate(orderDate.toString());
        order.setShipCity(orderForAddDto.getShipCity());
        order.setRequiredDate(orderForAddDto.getRequiredDate());
        order.setEmployee(employee);
        order.setCustomerId(customer.getCustomerId());
        Order order1 = orderRepository.save(order);
        int newOrderId = order1.getOrderId();
        List<OrderDetailsForAddDto> orderDetailsList = orderForAddDto.getOrderDetailsList();

        System.out.println(order1);

        for(OrderDetailsForAddDto orderDetailsForAddDto : orderDetailsList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(newOrderId);

            orderDetails.setProductId(orderDetailsForAddDto.getProductId());
            Product product = productRepository.findByProductId(orderDetailsForAddDto.getProductId());
            orderDetails.setUnitPrice(product.getUnitPrice());
            orderDetails.setDiscount(0);
            int unitsInStcokMax = product.getUnitsInStock();

            if(orderDetailsForAddDto.getQuantity() > unitsInStcokMax){
                orderDetails.setQuantity(unitsInStcokMax);
                productRepository.updateUnitsInStock(orderDetailsForAddDto.getProductId(), 0);
            } else {
                orderDetails.setQuantity(orderDetailsForAddDto.getQuantity());
                int newStock = unitsInStcokMax - orderDetailsForAddDto.getQuantity();
                productRepository.updateUnitsInStock(orderDetailsForAddDto.getProductId(), newStock);
            }

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

    @Override
    public List<Object[]> getOrdersWithProductNames() {
        return orderRepository.getOrdersWithProductNames();
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
