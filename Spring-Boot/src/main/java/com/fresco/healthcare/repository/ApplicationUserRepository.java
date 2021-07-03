package com.fresco.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fresco.healthcare.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

	public ApplicationUser findByUserEmail(String email);
	public ApplicationUser findByUsername(String username);
}
