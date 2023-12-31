package com.turkcell.spring.starter.business.conceretes;

import com.turkcell.spring.starter.business.abstracts.AuthService;
import com.turkcell.spring.starter.core.jwt.JwtService;
import com.turkcell.spring.starter.entities.User;
import com.turkcell.spring.starter.entities.UserRole;
import com.turkcell.spring.starter.entities.dtos.auth.AuthenticationResponse;
import com.turkcell.spring.starter.entities.dtos.auth.LoginRequest;
import com.turkcell.spring.starter.entities.dtos.auth.RegisterRequest;
import com.turkcell.spring.starter.repository.UserRepository;
import com.turkcell.spring.starter.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        List<String> roles = new ArrayList<>();

        for (Integer roleId : request.getUserRoleIdList()){
            UserRole userRole = userRoleRepository.findByRoleId(roleId);
            roles.add(userRole.getRoleName());
        }

        User user = User.builder().name(request.getName()).lastName(request.getLastName()).roles(roles).username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
