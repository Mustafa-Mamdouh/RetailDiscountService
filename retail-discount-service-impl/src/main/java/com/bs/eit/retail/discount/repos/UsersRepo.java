package com.bs.eit.retail.discount.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bs.eit.retail.discount.models.User;

public interface UsersRepo extends JpaRepository<User, Integer> {
	Optional<User> findByUserId(String userId);
}
