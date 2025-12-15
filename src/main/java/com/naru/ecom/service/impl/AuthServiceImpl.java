package com.naru.ecom.service.impl;

import com.naru.ecom.entity.User;
import com.naru.ecom.enums.Role;
import com.naru.ecom.exception.EmailAlreadyExistsException;
import com.naru.ecom.exception.InvalidCredentialsException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.naru.ecom.dto.LoginRequest;
import com.naru.ecom.dto.RegisterRequest;
import com.naru.ecom.repository.UserRepository;
import com.naru.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword((passwordEncoder.encode(request.getPassword())));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public void login(LoginRequest request) {
        // fetch user
         User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));

        // validate password
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }

      
    }

}
