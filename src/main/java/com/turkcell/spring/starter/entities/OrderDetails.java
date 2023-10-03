package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="order_details")
@Entity
public class OrderDetails {

    @Id
    @Column(name="order_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount")
    private double discount;

}
