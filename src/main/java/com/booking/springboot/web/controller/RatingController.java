package com.booking.springboot.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Rating;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.RatingService;
import com.booking.springboot.web.service.SeatService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
public class RatingController {

	@Autowired
	private RatingService ratingService;
	@Autowired
	private HappeningService happeningService;
	@Autowired
	private EstablishmentService establishmentService;
	@Autowired
	private GuestService guestService;

	// Kada setujemo seatlove bitno je da naglasimo u koji reon
	@RequestMapping(value = "/establishment/{eid}/rating/{user}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> rateEstablishment(@PathVariable int eid, @RequestBody String grade, @PathVariable int user) {
		Rating rating = new Rating();
		grade = grade.substring(1, grade.length() - 1);
		rating.setGrade(Integer.parseInt(grade));
		Guest guest = guestService.getOneById(user);
		rating.setGuest(guest);
		Rating r = ratingService.addNew(rating);
		Establishment est = establishmentService.getOneById(eid);
		Set<Rating> setRat = est.getRatings();
		setRat.add(r);
		int sum = 0;
		for (Rating rat : setRat) {
			sum += rat.getGrade();
		}
		double average = 1.0d * sum / setRat.size();
		est.setRatings(setRat);
		est.setRating(average);
		establishmentService.edit(est);
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Rating>(r, HttpStatus.OK);
	}

	@RequestMapping(value = "/happening/{hid}/rating/{user}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> rateHappening(@PathVariable int hid, @RequestBody String grade,
			@PathVariable int user) {
		Rating rating = new Rating();
		grade = grade.substring(1, grade.length() - 1);
		rating.setGrade(Integer.parseInt(grade));
		Guest guest = guestService.getOneById(user);
		rating.setGuest(guest);
		Rating r = ratingService.addNew(rating);
		Happening happ = happeningService.getOneById(hid);
		Set<Rating> setRat = happ.getRatings();
		setRat.add(r);
		int sum = 0;
		for (Rating rat : setRat) {
			sum += rat.getGrade();
		}
		double average = 1.0d * sum / setRat.size();
		happ.setRatings(setRat);
		happ.setRating(average);
		happeningService.edit(happ);
		if (r == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Rating>(r, HttpStatus.OK);
	}

}
