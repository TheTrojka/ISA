package com.booking.springboot.web.controller;

import java.util.ArrayList;
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

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Hall;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.HallService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.TimingService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establishment/{establishmentId}/hall/{hallId}/segment")
public class SegmentController {

	@Autowired
	private SegmentService segmentService;
	@Autowired
	private HallService hallService;
	@Autowired
	private BookedService bService;
	@Autowired
	private TimingService tService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Segment> getSviSegmenti(@PathVariable int hallId){
		return segmentService.getAll(hallId);
	}
	
	@RequestMapping(value = "/{id}",
					method = RequestMethod.GET,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> getById(@PathVariable int hallId, @PathVariable int id){
		Segment segment = segmentService.getOneById(hallId, id);
		if (segment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Segment>(segment, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> add(@RequestBody Segment segment, @PathVariable int establishmentId,
			@PathVariable int hallId){
		Segment exists = segmentService.getOneByHallAndName(hallId, segment.getName());
		if (exists != null) {
			return new ResponseEntity<Segment>(HttpStatus.BAD_REQUEST);
		}
		Segment seg = segment;
		Hall r = hallService.getOneById(establishmentId, hallId);
		seg.setHall(r);
		r.getSegments().add(seg);
		seg.setActive(true);
		Segment s = segmentService.addNew(seg);
		/*if (s == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}*/
		return new ResponseEntity<Segment>(s, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> updateSegment(@RequestBody Segment segmentparameter,
			@PathVariable int establishmentId,
			@PathVariable int hallId){
		Segment segment = segmentparameter;
		Segment h = segmentService.getOneById(hallId, segmentparameter.getId());
		Segment exists = segmentService.getOneByHallAndName(hallId, segmentparameter.getName());
		if (exists != null && h != exists) {
			return new ResponseEntity<Segment>(HttpStatus.BAD_REQUEST);
		}		
		if (segment.getName() != null) {
			h.setName(segment.getName());
		}
		Hall hall = hallService.getOneById(establishmentId, hallId);
		h.setHall(hall);
		hall.getSegments().add(h);
		Segment segmentRet = segmentService.addNew(h);
		return new ResponseEntity<Segment>(segmentRet, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> deleteSegment(@PathVariable int hallId, @PathVariable int id){
		Segment f = segmentService.getOneById(hallId, id);
		f.setActive(false);
		segmentService.editSegment(f);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/bookings",
			method = RequestMethod.GET, 
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Timing>> getSegmentBookings(@PathVariable int hallId,
			@PathVariable int id) {
		Segment segment = segmentService.getOneById(hallId, id);		
		ArrayList<Timing> timings = tService.getAll();
		ArrayList<Timing> returnTimings = new ArrayList<Timing>();
		for (Timing t : timings)
		{
			if (t.getSegment().contains(segment)) 
			{
				returnTimings.add(t);
			}
		}
		return new ResponseEntity<ArrayList<Timing>>(returnTimings, HttpStatus.OK);
	}
	
}

