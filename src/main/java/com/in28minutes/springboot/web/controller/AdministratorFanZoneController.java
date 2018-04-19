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



import com.booking.springboot.web.users.model.AdministratorFanZone;
import com.in28minutes.springboot.web.service.AdministratorFanZoneService;

@RestController
@RequestMapping("/adminfanzone")
public class AdministratorFanZoneController {
	
	@Autowired
	AdministratorFanZoneService service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<AdministratorFanZone> getAllFanZoneAdmins() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<AdministratorFanZone> getById(@PathVariable int id) {
		AdministratorFanZone f = service.getOneById(id);
		return new ResponseEntity<AdministratorFanZone>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdministratorFanZone> addAdministratorFanZone(AdministratorFanZone f) {
		service.addNew(f);
		return new ResponseEntity<AdministratorFanZone>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdministratorFanZone> deleteAdministratorFanZone(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdministratorFanZone> edit(AdministratorFanZone firma) {
		AdministratorFanZone f = service.edit(firma);
		return new ResponseEntity<AdministratorFanZone>(f, HttpStatus.OK);
	}
	

}
