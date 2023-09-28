package com.turkcell.spring.starter.business.conceretes;
import com.turkcell.spring.starter.business.abstracts.SalesService;
import com.turkcell.spring.starter.business.exceptions.BusinessException;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForAddDto;
import com.turkcell.spring.starter.entities.dtos.sales.SalesForUpdateDto;
import com.turkcell.spring.starter.repository.SalesRepository;
import com.turkcell.spring.starter.entities.Sales;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesManager implements SalesService {
    private final SalesRepository salesRepository;

    public SalesManager(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public void add(SalesForAddDto salesForAddDto) {
        requiredDateMustBeAfterOrderDate(salesForAddDto.getOrderDate(),salesForAddDto.getRequiredDate());
        shipCityMustNotBeEmpty(salesForAddDto.getShipCity());
        Sales sales = new Sales();
        sales.setFreight(salesForAddDto.getFreight());
        sales.setOrderDate(salesForAddDto.getOrderDate());
        sales.setShipCity(salesForAddDto.getShipCity());
        sales.setRequiredDate(salesForAddDto.getRequiredDate());

        salesRepository.save(sales);
    }

    @Override
    public void delete(int id) {

        deleteOrderMustExist(id);
        salesRepository.deleteById(id);
    }

    @Override
    public void update(int id, SalesForUpdateDto salesForUpdateDto) {
        salesRepository.updateSales(id, salesForUpdateDto.getFreight(),salesForUpdateDto.getShipCity(), salesForUpdateDto.getShipCountry());
    }

    @Override
    public List<Sales> getAll() {
        return salesRepository.findAll();
    }

    @Override
    public Sales getById(int id) {
        return salesRepository.findByOrderId(id);
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
        Sales existingOrder = salesRepository.findByOrderId(orderId);
        if (existingOrder == null) {
            throw new BusinessException("Silinecek sipariş bulunamadı.");
        }
    }



}
