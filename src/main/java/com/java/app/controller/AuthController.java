package com.java.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.app.Service.AuthService;
import com.java.app.dto.ApiResponse;
import com.java.app.dto.LoginRequest;
import com.java.app.entity.User;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private AuthService authService;

	 @PostMapping("/login")
	    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest request) {
	        String result = authService.login(request.getEmail(), request.getPassword());
	        return ResponseEntity.ok(new ApiResponse<>("success", result, null));
	    }

	    @PostMapping("/register")
	    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
	        User savedUser = authService.registerUser(user);
	        return ResponseEntity.ok(new ApiResponse<>("success", "User registered", savedUser));
	    }
}
