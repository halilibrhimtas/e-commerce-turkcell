package com.turkcell.spring.starter.repository;

import com.turkcell.spring.starter.entities.User;
import com.turkcell.spring.starter.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository  extends JpaRepository<UserRole, Integer> {

    UserRole findByRoleId(Integer integer);

}
