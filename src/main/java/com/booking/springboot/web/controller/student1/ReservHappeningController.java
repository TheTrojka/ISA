package com.booking.springboot.web.controller.student1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.service.HappeningService;


public class ReservHappeningController {
	@Autowired
	private HappeningService hapService;
	

	@RequestMapping(value = "/happening",
					method = RequestMethod.GET)
	public ArrayList<Happening> getSvi(@PathVariable int fakturaId){
		return hapService.getAllByEstablishment(fakturaId);
	}
	
	@RequestMapping(value = "/happening/{id}",
					method = RequestMethod.GET,
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> getById(@PathVariable int reservId, @PathVariable int id){
		Happening hap = hapService.getAllByOneReservation(reservId, id);
		if (hap == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Happening>(hap, HttpStatus.OK);
	}
	
}
