package com.turkcell.spring.starter.repository;

import java.util.List;

import com.turkcell.spring.starter.entities.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByShipCity(String shipCity);
    Order findByOrderId(int orderId);
    Order findByFreight(double freight);

    @Transactional
    @Modifying
    @Query("UPDATE Order s SET s.freight = :freight, s.shipCity = :shipCity, s.shipCountry = :shipCountry WHERE s.orderId = :orderId")
    void updateOrder(@Param("orderId") int orderId, @Param("freight") double freight, @Param("shipCity") String shipCity, @Param("shipCountry") String shipCountry);

}