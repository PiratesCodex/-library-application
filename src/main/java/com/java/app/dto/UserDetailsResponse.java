package com.java.app.dto;

import java.util.List;

public class UserDetailsResponse {
	private Long userId;
	private String name;
	private String email;
	private BookSummary currentBook;
	private List<BookSummary> pastBooks;

	public static class BookSummary {
		private Long bookId;
		private String name;
		private String dueDate;
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDueDate() {
			return dueDate;
		}
		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}
		public BookSummary(Long bookId, String name, String dueDate) {
			super();
			this.bookId = bookId;
			this.name = name;
			this.dueDate = dueDate;
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BookSummary getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(BookSummary currentBook) {
		this.currentBook = currentBook;
	}

	public List<BookSummary> getPastBooks() {
		return pastBooks;
	}

	public void setPastBooks(List<BookSummary> pastBooks) {
		this.pastBooks = pastBooks;
	}
}
