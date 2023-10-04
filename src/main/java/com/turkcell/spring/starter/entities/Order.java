package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="orders")
@Entity
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

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

    @Column(name = "customer_id")
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDate + '\'' +
                ", requiredDate='" + requiredDate + '\'' +
                ", freight=" + freight +
                ", shipCity='" + shipCity + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                ", customerId='" + customerId + '\'' +
                ", employee=" + employee +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }
}
