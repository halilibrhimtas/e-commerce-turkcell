package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name="employees")
@Entity
public class Employee {

    @Id
    @Column(name="employee_id", nullable = false, unique = true)
    private int employeeId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="title")
    private String title;

    @Column(name="title_of_courtesy")
    private String titleOfCourtesy;

    @Column(name="birth_date")
    private String birthDate;

    @Column(name="hire_date")
    private String hireDate;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="region")
    private String region;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country")
    private String country;

    @Column(name="home_phone")
    private String homePhone;

    @Column(name="extension")
    private String extension;

    @Column(name="notes", columnDefinition = "text")
    private String notes;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", titleOfCourtesy='" + titleOfCourtesy + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", extension='" + extension + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

}
