package com.java.app.entity;

import java.time.LocalDate;

import com.java.app.entity.LibraryEnums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private LocalDate membershipStartDate;
	private LocalDate membershipEndDate;

	@Enumerated(EnumType.STRING)
	private UserType userType;
	// getters and setters
	
	public Long getUserId() {
		return userId;
	}
	
	

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getMembershipStartDate() {
		return membershipStartDate;
	}

	public void setMembershipStartDate(LocalDate membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}

	public LocalDate getMembershipEndDate() {
		return membershipEndDate;
	}

	public void setMembershipEndDate(LocalDate membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", membershipStartDate=" + membershipStartDate + ", membershipEndDate=" + membershipEndDate
				+ ", userType=" + userType + "]";
	}

	public User(Long userId, String name, String email, String password, LocalDate membershipStartDate,
			LocalDate membershipEndDate, UserType userType) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.membershipStartDate = membershipStartDate;
		this.membershipEndDate = membershipEndDate;
		this.userType = userType;
	}
}
