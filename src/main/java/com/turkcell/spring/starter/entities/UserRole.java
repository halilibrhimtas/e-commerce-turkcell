package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue()
    private int roleId;

    @Column(name = "role_name")
    private String roleName;
}
