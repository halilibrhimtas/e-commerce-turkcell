package com.turkcell.spring.starter.repository;

import com.turkcell.spring.starter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
    Customer findByCustomerStringId(String customerId);

}
