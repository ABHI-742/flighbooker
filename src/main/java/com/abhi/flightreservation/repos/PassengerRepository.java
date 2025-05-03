package com.abhi.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
