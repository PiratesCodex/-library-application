package com.java.app.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.app.dto.CategoryPopularityResponse;
import com.java.app.entity.Book;
import com.java.app.entity.LibraryEnums.BookStatus;
import com.java.app.entity.User;
import com.java.app.exception.BadRequestException;
import com.java.app.exception.ResourceNotFoundException;
import com.java.app.repository.BookRepository;
import com.java.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Book> getBooks(String category, BookStatus status, String author, String name) {
		return bookRepository.searchBooks(category, status, author, name);
	}

	public Book addBook(Book book) {
		book.setStatus(BookStatus.AVAILABLE);
		return bookRepository.save(book);
	}

	public String updateBookStatus(Long bookId, BookStatus status) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
		book.setStatus(status);
		if (status == BookStatus.AVAILABLE) {
			book.setTakenBy(null);
			book.setDueDate(null);
		}
		bookRepository.save(book);
		return "Book status updated";
	}

	@Transactional
	public String borrowBook(Long userId, Long bookId, int days) {
		if (days <= 0)
			throw new BadRequestException("Days must be positive");
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

		if (book.getStatus() != BookStatus.AVAILABLE)
			return "Book is not available";
		if (user.getMembershipEndDate() == null || user.getMembershipEndDate().isBefore(LocalDate.now()))
			return "Membership expired or not active";

		book.setStatus(BookStatus.TAKEN);
		book.setTakenBy(user);
		book.setDueDate(LocalDate.now().plusDays(days));
		bookRepository.save(book);

		return "Book borrowed successfully. Due date: " + book.getDueDate();
	}
	
	public Book getBookById(Long id) {
	    return bookRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
	}
	public void deleteBook(Long id) {
	    if (!bookRepository.existsById(id)) {
	        throw new RuntimeException("Book not found with id: " + id);
	    }
	    bookRepository.deleteById(id);
	}

	public List<CategoryPopularityResponse> getCategoryPopularity() {
		List<Book> allBooks = bookRepository.findAll();
		Map<String, Set<Long>> categoryUserMap = new HashMap<>();

		for (Book book : allBooks) {
			if (book.getTakenBy() != null) {
				categoryUserMap.computeIfAbsent(book.getCategory() == null ? "UNKNOWN" : book.getCategory(),
						k -> new HashSet<>()).add(book.getTakenBy().getUserId());
			}
		}
		Set<Long> allBorrowers = categoryUserMap.values().stream().flatMap(Set::stream).collect(Collectors.toSet());

		int totalUsers = allBorrowers.size();
		List<CategoryPopularityResponse> result = new ArrayList<>();
		for (var entry : categoryUserMap.entrySet()) {
			double percentage = totalUsers == 0 ? 0.0 : (entry.getValue().size() * 100.0) / totalUsers;
			result.add(new CategoryPopularityResponse(entry.getKey(), percentage));
		}
		return result;
	}
}
