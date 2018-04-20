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


import com.booking.springboot.web.service.SystemAdministratorService;

import com.booking.springboot.web.users.model.SystemAdministrator;


@RestController
@RequestMapping("/sysadmin")
public class SystemAdministratorController {

	@Autowired
	SystemAdministratorService service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<SystemAdministrator> getAllSysAdmins() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<SystemAdministrator> getById(@PathVariable int id) {
		SystemAdministrator f = service.getOneById(id);
		return new ResponseEntity<SystemAdministrator>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SystemAdministrator> addSysAdministrator(SystemAdministrator f) {
		service.addNew(f);
		return new ResponseEntity<SystemAdministrator>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SystemAdministrator> deleteSysAdministrator(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SystemAdministrator> edit(SystemAdministrator firma) {
		SystemAdministrator f = service.edit(firma);
		return new ResponseEntity<SystemAdministrator>(f, HttpStatus.OK);
	}
	
}
