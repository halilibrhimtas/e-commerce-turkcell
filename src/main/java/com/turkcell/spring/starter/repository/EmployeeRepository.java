package com.turkcell.spring.starter.repository;

import com.turkcell.spring.starter.entities.Customer;
import com.turkcell.spring.starter.entities.Employee;
import com.turkcell.spring.starter.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
    Employee findByEmployeeId(int employeeId);
}
