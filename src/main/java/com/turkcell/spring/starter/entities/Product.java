package com.turkcell.spring.starter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Table(name="products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne()
    @JoinColumn(name="category_id")
    @JsonIgnore
    private Category category;

    /*
    @Column(name = "supplier_id")
    private int supplierId;
    */
    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "units_on_order")
    private int unitsOnOrder;

    @Column(name = "reorder_level")
    private int reorderLevel;

    @Column(name = "discontinued")
    private int discontinued;
}
