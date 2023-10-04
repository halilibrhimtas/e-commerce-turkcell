package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="customers")
@Entity
public class Customer {
    @Id
    @Column(name="customer_id", nullable = false, unique = true)
    private String customerId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="contact_title")
    private String contactTitle;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="region")
    private String region;

    @Column(name="postal_code")
    private String postal_code;

    @Column(name="country")
    private String country;

    @Column(name="phone")
    private String phone;

    @Column(name="fax")
    private String fax;
}
