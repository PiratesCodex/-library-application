package com.java.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.app.Service.MembershipService;
import com.java.app.dto.ApiResponse;

@RestController
@RequestMapping("/api/membership")
public class MembershipController {
	@Autowired
	private MembershipService membershipService;

	@GetMapping("/validity")
	public ResponseEntity<ApiResponse<Map<String, Object>>> checkValidity(@RequestParam Long userId) {
		Map<String, Object> result = membershipService.checkValidity(userId);
		return ResponseEntity.ok(new ApiResponse<>("success", "Membership status fetched", result));
	}
}
