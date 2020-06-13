package com.ups.retail.discount.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ups.retail.discount.models.User;

public interface UsersRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUserId(String userId);
}
