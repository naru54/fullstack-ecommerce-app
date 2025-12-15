package com.naru.ecom.service;

import com.naru.ecom.dto.LoginRequest;
import com.naru.ecom.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    void login(LoginRequest request);
}


