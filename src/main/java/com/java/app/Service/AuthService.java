package com.java.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.app.entity.User;
import com.java.app.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	public String login(String email, String password) {
//		return userRepository.findByEmail(email).filter(user -> passwordEncoder.matches(password, user.getPassword()))
//				.map(u -> "Login successful for user: " + u.getName()).orElse("Invalid credentials");
//	}

	    // Login method
	    public String login(String email, String password) {
	        return userRepository.findByEmail(email)
	                .filter(user -> passwordEncoder.matches(password, user.getPassword())) // check encoded password
	                .map(u -> "Login successful for user: " + u.getName())
	                .orElse("Invalid credentials");
	    }

	    // Optional: method to register a new user with encoded password
	    public User registerUser(User user) {
	        // Encode the password before saving
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        return userRepository.save(user);
	    }
}
