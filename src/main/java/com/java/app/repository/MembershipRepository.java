package com.java.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.app.entity.Membership;
import com.java.app.entity.User;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

	Optional<Membership> findByUser(User user);
}
