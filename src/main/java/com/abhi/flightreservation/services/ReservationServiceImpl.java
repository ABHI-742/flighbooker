package com.abhi.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhi.flightreservation.controllers.FlightController;
import com.abhi.flightreservation.dto.ReservationRequest;
import com.abhi.flightreservation.entities.Flight;
import com.abhi.flightreservation.entities.Passenger;
import com.abhi.flightreservation.entities.Reservation;
import com.abhi.flightreservation.repos.FlightRepository;
import com.abhi.flightreservation.repos.PassengerRepository;
import com.abhi.flightreservation.repos.ReservationRepository;
import com.abhi.flightreservation.util.EmailUtil;
import com.abhi.flightreservation.util.PDFGenerator;



@Service
public class ReservationServiceImpl implements ReservationService {

	
	@Value("${com.abhi.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	
	
	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;
	
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
	
		
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		LOGGER.info("Generating  the itinerary"+filePath);
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		
		return savedReservation;
	}

}
