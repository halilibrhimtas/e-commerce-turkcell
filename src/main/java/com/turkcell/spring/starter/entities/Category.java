package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data // getter+setter
@Table(name="categories")
@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    private List<Product> products;
}
