package com.abhi.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.flightreservation.entities.Flight;
import com.abhi.flightreservation.repos.FlightRepository;

@Controller
public class FlightController 
{
	@Autowired
    private FlightRepository flightRepository;
	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(FlightController.class);
	
	@RequestMapping("/findFlights")
	public String findFlight(@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("departureDate") @DateTimeFormat(pattern="MM-dd-yyyy")Date departureDate,ModelMap modelMap)
	{
		LOGGER.info("Inside findFlight() From: "+from+"To: "+to+"Departure Date: "+departureDate);
	    List<Flight> flights=flightRepository.findFlights(from,to,departureDate);	
		modelMap.addAttribute("flights",flights);
		LOGGER.info("Flights Found are:"+flights.toString());
        return "/displayFlights";
	}
	
	

}
