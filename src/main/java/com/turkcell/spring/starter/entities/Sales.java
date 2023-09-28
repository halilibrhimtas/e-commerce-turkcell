package com.turkcell.spring.starter.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sales {
    public String customer;
    public int numberOfSales;
    public int discount;
    public int id;
}
