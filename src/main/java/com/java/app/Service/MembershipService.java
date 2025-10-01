package com.java.app.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.app.entity.User;
import com.java.app.repository.UserRepository;

@Service
public class MembershipService {
	@Autowired
	private UserRepository userRepository;

	public Map<String, Object> checkValidity(Long userId) {
		User user = userRepository.findById(userId).orElseThrow();
		boolean valid = user.getMembershipEndDate() != null && user.getMembershipEndDate().isAfter(LocalDate.now());

		Map<String, Object> response = new HashMap<>();
		response.put("valid", valid);
		response.put("membership_end_date", user.getMembershipEndDate());
		return response;
	}
}
