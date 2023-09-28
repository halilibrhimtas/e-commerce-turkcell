package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Table(name="orders")
@Entity
public class Sales {
    @Id
    @GeneratedValue
    @Column(name="order_id")
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
}
