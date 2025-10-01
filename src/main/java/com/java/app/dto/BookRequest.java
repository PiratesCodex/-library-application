package com.java.app.dto;

public class BookRequest {
	private String name;
	private String author;
	private String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "BookRequest [name=" + name + ", author=" + author + ", category=" + category + "]";
	}
}