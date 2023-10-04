package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="order_details")
@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private int orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable=false, updatable=false)
    private Order order;

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
    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}
