package com.java.app.dto;

public class BorrowRequest {
	private Long userId;
	private Long bookId;
	private int days;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	@Override
	public String toString() {
		return "BorrowRequest [userId=" + userId + ", bookId=" + bookId + ", days=" + days + "]";
	}
}
