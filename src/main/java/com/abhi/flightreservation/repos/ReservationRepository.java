package com.abhi.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
