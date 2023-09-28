package com.turkcell.spring.starter.repository;

import java.util.List;

import com.turkcell.spring.starter.entities.Category;
import com.turkcell.spring.starter.entities.Product;
import com.turkcell.spring.starter.entities.Sales;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    List<Sales> findByShipCity(String shipCity);
    Sales findByOrderId(int orderId);
    Sales findByFreight(double freight);

    @Transactional
    @Modifying
    @Query("UPDATE Sales s SET s.freight = :freight, s.shipCity = :shipCity, s.shipCountry = :shipCountry WHERE s.orderId = :orderId")
    void updateSales(@Param("orderId") int productId, @Param("freight") double freight, @Param("shipCity") String shipCity, @Param("shipCountry") String shipCountry);
}