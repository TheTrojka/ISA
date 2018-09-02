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

import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.FanZone;
import com.booking.springboot.web.model.Props;
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.service.FanZoneService;
import com.booking.springboot.web.service.PropsService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fanzone/props")
public class PropsController {
	
	@Autowired
	PropsService service;
	
	@Autowired
	FanZoneService fService;
	
	@Autowired
	GuestService gService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Props> getAllProps() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Props> getById(@PathVariable int id) {
		Props f = service.getOneById(id);
		return new ResponseEntity<Props>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Props> add(@RequestBody Props pr){
		Props exist = service.getOneByName(pr.getName());
		if(exist != null)
		{
			return new ResponseEntity<Props>(pr, HttpStatus.BAD_REQUEST);
		}
		 service.addNew(pr);
		return new ResponseEntity<Props>(pr, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Props> deleteProps(@PathVariable int id){
		Props f = service.getOneById(id);
		f.setActive(false);
		service.edit(f);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Props> update(@RequestBody Props pr ){
		Props exist = service.getOneByName(pr.getName());
		if(exist != null)
		{
			return new ResponseEntity<Props>(pr, HttpStatus.BAD_REQUEST);
		}
		Props prop = service.edit(pr);
		return new ResponseEntity<Props>(prop, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/buy/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Props> buy(@PathVariable int id, @PathVariable int userId) {
		Props f = service.getOneById(id);
		Guest guest = gService.getOneById(userId);
		guest.getProps().add(f);
		gService.editGuest(guest);
		return new ResponseEntity<Props>(f, HttpStatus.OK);
	}




}
