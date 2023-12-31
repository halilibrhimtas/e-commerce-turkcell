package com.turkcell.spring.starter.entities;

import com.turkcell.spring.starter.repository.converters.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue()
    private Integer id;

    private String name;
    private String lastName;
    private String username;
    private String password;

    @Convert(converter = StringListConverter.class)
    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // roller
        // todo: refactor with multiple roles
        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        for(String role : roles) {
            roleList.add(new SimpleGrantedAuthority(role));
        }
        return roleList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
