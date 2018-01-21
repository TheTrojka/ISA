package com.in28minutes.springboot.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.web.model.Seat;
import com.in28minutes.springboot.web.service.EstablishmentService;
import com.in28minutes.springboot.web.service.SeatService;
import com.in28minutes.springboot.web.service.SegmentService;

@RestController
@RequestMapping("/establishment/{establishmentId}/segment/{segmentId}")
public class SeatController {

	@Autowired
	private SeatService seatService;
	@Autowired
	private SegmentService segmentService;
	@Autowired
	private EstablishmentService establishmentService;
	
	// Kada getujemo seatlove (za rez npr) nije nam bitno u kojem su reonu
	// to se tice konobara samo.
	@RequestMapping(value = "/seat",
					method = RequestMethod.GET)
	public ArrayList<Seat> getSvi(@PathVariable int establishmentId, @PathVariable int segmentId){
		return seatService.getAll(establishmentId, segmentId);
	}
	
	@RequestMapping(value = "/seat/{id}",
					method = RequestMethod.GET,
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
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
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seat> add(Seat seat, @PathVariable int establishmentId, @PathVariable int segmentId){
		Seat st = seat;
		st.setEstablishment(establishmentService.getOneById(establishmentId));
		st.setSegment(segmentService.getOneById(establishmentId, segmentId));
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
//	@RequestMapping(value = "/{id}",
//			method = RequestMethod.DELETE,
//			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Segment> deleteSegment(@PathVariable int id){
//		segmentService.deleteSegment(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}

