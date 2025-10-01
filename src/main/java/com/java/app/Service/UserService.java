package com.java.app.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.app.dto.UserDetailsResponse;
import com.java.app.entity.Book;
import com.java.app.entity.LibraryEnums.BookStatus;
import com.java.app.entity.User;
import com.java.app.exception.ResourceNotFoundException;
import com.java.app.repository.BookRepository;
import com.java.app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers() { return userRepository.findAll(); }


	public UserDetailsResponse getUserDetailsStructured(Long id) {
	User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));


	UserDetailsResponse resp = new UserDetailsResponse();
	resp.setUserId(user.getUserId());
	resp.setName(user.getName());
	resp.setEmail(user.getEmail());


	List<UserDetailsResponse.BookSummary> pastBooks = new ArrayList<>();
	List<Book> userBooks = bookRepository.findByTakenBy_UserId(user.getUserId());


	for (Book book : userBooks) {
	if (book.getStatus() == BookStatus.TAKEN) {
	resp.setCurrentBook(new UserDetailsResponse.BookSummary(book.getBookId(), book.getName(), String.valueOf(book.getDueDate())));
	} else {
	pastBooks.add(new UserDetailsResponse.BookSummary(book.getBookId(), book.getName(), null));
	}
	}
	resp.setPastBooks(pastBooks);
	return resp;
	}
	
	// Get single user
	public User getUserById(Long id) {
	    return userRepository.findById(id).orElse(null);
	}

	// Update user
	public User updateUser(Long id, User updatedUser) {
	    return userRepository.findById(id).map(user -> {
	        user.setName(updatedUser.getName());
	        user.setEmail(updatedUser.getEmail());
	        // optionally update password, encode it if needed
	        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
	            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	        }
	        return userRepository.save(user);
	    }).orElse(null);
	}

	// Delete user
	public boolean deleteUser(Long id) {
	    if (userRepository.existsById(id)) {
	        userRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}

}
