package com.abhi.flightreservation.services;

import com.abhi.flightreservation.dto.ReservationRequest;
import com.abhi.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
