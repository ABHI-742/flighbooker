package com.abhi.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
