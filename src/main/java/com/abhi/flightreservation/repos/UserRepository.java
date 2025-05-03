package com.abhi.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
}
