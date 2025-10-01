package com.java.app.dto;

public class CategoryPopularityResponse {
	private String category;
	private double percentage;

	public CategoryPopularityResponse(String category, double percentage) {
		this.category = category;
		this.percentage = percentage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "CategoryPopularityResponse [category=" + category + ", percentage=" + percentage + "]";
	}
}
