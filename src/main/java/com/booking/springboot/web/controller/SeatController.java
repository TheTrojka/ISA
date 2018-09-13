package com.booking.springboot.web.controller;

import java.util.ArrayList;

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

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.SeatService;
import com.booking.springboot.web.service.SegmentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establishment/{establishmentId}/hall/{hallId}/segment/{segmentId}")
public class SeatController {

	@Autowired
	private SeatService seatService;
	@Autowired
	private SegmentService segmentService;
	@Autowired
	private EstablishmentService establishmentService;
	@Autowired
	private BookedService bService;
	
	// Kada getujemo seatlove (za rez npr) nije nam bitno u kojem su reonu
	// to se tice konobara samo.
	@RequestMapping(value = "/seat",
					method = RequestMethod.GET)
	public ArrayList<Seat> getSvi(@PathVariable int establishmentId, @PathVariable int segmentId){
		System.out.println(establishmentId + "calls" + segmentId);
		return seatService.getAll(establishmentId, segmentId);
	}
	
	@RequestMapping(value = "/seat/{id}",
					method = RequestMethod.GET,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seat> getById(@PathVariable int establishmentId, @PathVariable int segmentId, @PathVariable int id){
		Seat seat = seatService.getOneById(establishmentId, segmentId, id);
		if (seat == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Seat>(seat, HttpStatus.OK);
	}
	
	// Kada setujemo seatlove bitno je da naglasimo u koji reon
	@RequestMapping(value = "/seat",
					method = RequestMethod.POST, 
					consumes = MediaType.ALL_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seat> add(@PathVariable int establishmentId,
			@PathVariable int hallId, @PathVariable int segmentId){
		Seat st = new Seat();
		st.setActive(true);
		st.setEstablishment(establishmentService.getOneById(establishmentId));
		st.setSegment(segmentService.getOneById(hallId, segmentId));
//		st.setMatR(rezervacijaService.getOneById(gostId, id));
		Seat s = seatService.addNew(st);
		if (s == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Seat>(s, HttpStatus.OK);
	}
	
	
//	@RequestMapping(value = "/{id}",
//			method = RequestMethod.PUT,
//			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Segment> updateSegment(Segment segment, @PathVariable int id){
//		Segment s = segmentService.editSegment(segment);
//		if (s != null){
//			return new ResponseEntity<Segment>(s, HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
	@RequestMapping(value = "/seat/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Seat> deleteStavka(@PathVariable int establishmentId, @PathVariable int segmentId,
			@PathVariable int id) {
		Seat f = seatService.getOneById(establishmentId, segmentId, id);
		f.setActive(false);
		seatService.editSeat(f);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/seat/{id}/bookings",
			method = RequestMethod.GET, 
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Timing>> getSeatBookings(@PathVariable int establishmentId, @PathVariable int segmentId,
			@PathVariable int id) {
		ArrayList<Booked> booked = bService.getBySeatId(id);
		ArrayList<Timing> timings = new ArrayList<Timing>();
		for(Booked b:booked) {
			timings.add(b.getTiming());
		}
		return new ResponseEntity<ArrayList<Timing>>(timings, HttpStatus.OK);
	}
	
}

