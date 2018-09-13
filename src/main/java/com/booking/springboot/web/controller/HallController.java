package com.booking.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.model.Hall;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HallService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.TimingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establishment/{establishmentId}/hall")
public class HallController {

	@Autowired
	private HallService hallService;
	@Autowired
	private EstablishmentService establishmentService;
	@Autowired
	private TimingService tService;
	@Autowired
	private HappeningService hService;


	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Hall> getSviHalli(@PathVariable int establishmentId) {
		return hallService.getAll(establishmentId);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hall> getById(@PathVariable int establishmentId, @PathVariable int id) {
		Hall hall = hallService.getOneById(establishmentId, id);
		if (hall == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Hall>(hall, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hall> add(@RequestBody Hall hall, @PathVariable int establishmentId) {
		Hall exists = hallService.getOneByEstablishmentAndName(establishmentId, hall.getName());
		if (exists != null) {
			return new ResponseEntity<Hall>(HttpStatus.BAD_REQUEST);
		}
		Hall seg = hall;
		Establishment r = establishmentService.getOneById(establishmentId);
		seg.setEstablishment(r);
		r.getHalls().add(seg);
		hall.setActive(true);
		Hall s = hallService.addNew(seg);
		/*
		 * if (s == null){ return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
		 */
		return new ResponseEntity<Hall>(s, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hall> updateHall(@RequestBody Hall hallparameter, @PathVariable int establishmentId) {
		Hall exists = hallService.getOneByEstablishmentAndName(establishmentId, hallparameter.getName());
		Hall h = hallService.getOneById(establishmentId, hallparameter.getId());
		if (exists != null && h != exists) {
			return new ResponseEntity<Hall>(HttpStatus.BAD_REQUEST);
		}
		Hall hall = hallparameter;
		if (hall.getName() != null) {
			h.setName(hall.getName());
		}
		Establishment establishment = establishmentService.getOneById(establishmentId);
		h.setEstablishment(establishment);
		establishment.getHalls().add(hall);
		Hall hallRet = hallService.addNew(h);
		return new ResponseEntity<Hall>(hallRet, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hall> deleteHall(@PathVariable int establishmentId, @PathVariable int id){
		Hall f = hallService.getOneById(establishmentId, id);
		f.setActive(false);
		hallService.editHall(f);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/bookings", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Timing>> getHallBookings(@PathVariable int establishmentId, @PathVariable int id) {
		Hall hall = hallService.getOneById(establishmentId, id);
		ArrayList<Timing> timings = tService.getAll();
		ArrayList<Timing> returnTimings = new ArrayList<Timing>();
		for (Timing t : timings) {
			for (Segment segment : hall.getSegments()) {
				if (t.getSegment().contains(segment)) {
					returnTimings.add(t);
				}
			}
		}
		return new ResponseEntity<ArrayList<Timing>>(returnTimings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/busy", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Timing> getHallBookings(@PathVariable int id) {
		Timing timing = tService.getOneById(id);
		ArrayList<Happening> happs = hService.getAll(); 
		Happening happening = happs.stream()
				.filter(x -> x.getTimings().contains(timing))
        		.findFirst()
        		.get();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(timing.getTime());
		calendar.add(Calendar.MINUTE, happening.getDuration());
		Date f = calendar.getTime();
		timing.setTime(calendar.getTime());
		return new ResponseEntity<Timing>(timing, HttpStatus.OK);
	}

}
