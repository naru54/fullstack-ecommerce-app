package com.naru.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naru.ecom.dto.LoginRequest;
import com.naru.ecom.dto.RegisterRequest;
import com.naru.ecom.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

   private final AuthService authService;

   @PostMapping("/register")
   public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
      authService.register(request);
      return ResponseEntity.ok("User registered Successfully");
   }

   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody LoginRequest request){
      authService.login(request);
      return ResponseEntity.ok("Login Successful");
   }


}
