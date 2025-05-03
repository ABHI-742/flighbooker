package com.abhi.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abhi.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query(value="select * from Flight where Arrival_City=:arrivalCity and Departure_City=:departureCity and DATE_OF_DEPARTURE=:dateOfDepature",nativeQuery=true)
	List<Flight> findFlights(@Param("departureCity")String from, @Param("arrivalCity")String to, @Param("dateOfDepature")Date departureDate);
}
