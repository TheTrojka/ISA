package com.booking.springboot.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.SegmentService;


@RestController
@RequestMapping("/establishment/{establishmentId}/segment")
public class SegmentController {

	@Autowired
	private SegmentService segmentService;
	@Autowired
	private EstablishmentService establishmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Segment> getSviSegmenti(@PathVariable int establishmentId){
		return segmentService.getAll(establishmentId);
	}
	
	@RequestMapping(value = "/{id}",
					method = RequestMethod.GET,
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> getById(@PathVariable int establishmentId, @PathVariable int id){
		Segment segment = segmentService.getOneById(establishmentId, id);
		if (segment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Segment>(segment, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> add(Segment segment, @PathVariable int establishmentId){
		Segment seg = segment;
		Establishment r = establishmentService.getOneById(establishmentId);
		seg.setEstablishment(r);
		r.getSegments().add(seg);
		
		Segment s = segmentService.addNew(seg);
		if (s == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Segment>(s, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> updateSegment(Segment segment, @PathVariable int id){
		Segment s = segmentService.editSegment(segment);
		if (s != null){
			return new ResponseEntity<Segment>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> deleteSegment(@PathVariable int id){
		segmentService.deleteSegment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

