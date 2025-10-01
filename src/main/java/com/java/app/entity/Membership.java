package com.java.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membership")
public class Membership {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membershipId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer membershipMonths;

    
    
    
	public Membership() {
	
	}

	public Long getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getMembershipMonths() {
		return membershipMonths;
	}

	public void setMembershipMonths(Integer membershipMonths) {
		this.membershipMonths = membershipMonths;
	}

	@Override
	public String toString() {
		return "Membership [membershipId=" + membershipId + ", user=" + user + ", membershipMonths=" + membershipMonths
				+ "]";
	}
}
