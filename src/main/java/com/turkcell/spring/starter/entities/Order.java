package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="orders")
@Entity
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_id", nullable = false,unique = true)
    private int orderId;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "required_date")
    private String requiredDate;

    @Column(name = "freight")
    private double freight;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_country")
    private String shipCountry;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetailsList = new ArrayList<>();
}
