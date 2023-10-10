package com.turkcell.spring.starter.business.conceretes;
import com.turkcell.spring.starter.business.abstracts.OrderService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.*;
import com.turkcell.spring.starter.entities.dtos.order.OrderForAddDto;
import com.turkcell.spring.starter.entities.dtos.order.OrderForUpdateDto;
import com.turkcell.spring.starter.entities.dtos.orderDetails.OrderDetailsForAddDto;
import com.turkcell.spring.starter.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final MessageSource messageSource;


    @Override
    @Transactional // metotun tamamen başarılı bir şekilde bitmesini bekleyip değişiklikleri o şekilde pushlayan metot
    public void add(OrderForAddDto orderForAddDto) {
        requiredDateMustBeAfterOrderDate( orderForAddDto.getRequiredDate());
        shipCityMustNotBeEmpty(orderForAddDto);

        Customer customer = customerRepository.findByCustomerStringId(orderForAddDto.getCustomerId());

        if (customer == null) {
            throw new BusinessException(messageSource.getMessage("customerNotFound", new Object[] {orderForAddDto.getCustomerId()}, LocaleContextHolder.getLocale()));
        }

        Employee employee = employeeRepository.findByEmployeeId(orderForAddDto.getEmployeeId());
        if (employee == null) {
            throw new BusinessException(messageSource.getMessage("employeeNotFound", new Object[] {orderForAddDto.getEmployeeId()}, LocaleContextHolder.getLocale()));
        }

        /*
        LocalDate orderDate = LocalDate.now();

        LocalDate requiredDate = LocalDate.parse(orderForAddDto.getRequiredDate());
        if (requiredDate.isBefore(orderDate)) {
            throw new BusinessException("Required Date, Order Date'den önce olamaz.");
        }
        */

        /*
        Order order = new Order();
        order.setFreight(orderForAddDto.getFreight());
        order.setOrderDate(orderDate.toString());
        order.setShipCity(orderForAddDto.getShipCity());
        order.setRequiredDate(orderForAddDto.getRequiredDate());
        order.setEmployee(employee);
        order.setCustomerId(customer.getCustomerId());

         */
        Order orderFromAutoMapping = modelMapper.map(orderForAddDto, Order.class);

        Order order1 = orderRepository.save(orderFromAutoMapping);

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

    private void requiredDateMustBeAfterOrderDate(String requiredDate) {
        LocalDate parsedRequiredDate = LocalDate.parse(requiredDate);
        if (parsedRequiredDate.isBefore(LocalDate.now())) {
            throw new BusinessException(messageSource.getMessage("requiredDateMustBeAfterOrderDate", new Object[] {LocalDate.now()}, LocaleContextHolder.getLocale()));
        }
    }

    private void shipCityMustNotBeEmpty(OrderForAddDto orderForAddDto) {
        if (orderForAddDto.getShipCity() == null || orderForAddDto.getShipCity().trim().isEmpty()) {
            throw new BusinessException(messageSource.getMessage("shipCityMustNotBeEmpty", new Object[] {orderForAddDto.getCustomerId()}, LocaleContextHolder.getLocale()));
        }
    }

    private void deleteOrderMustExist(int orderId) {
        Order existingOrder = orderRepository.findByOrderId(orderId);
        if (existingOrder == null) {
            throw new BusinessException(messageSource.getMessage("deleteOrderMustExist", new Object[] {orderId}, LocaleContextHolder.getLocale()));
        }
    }
}
