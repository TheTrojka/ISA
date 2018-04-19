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


import com.in28minutes.springboot.web.model.UsedProps;

import com.in28minutes.springboot.web.service.UsedPropsService;

@RestController
@RequestMapping("/usedprops")
public class UsedPropsController {

	@Autowired
	UsedPropsService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<UsedProps> getAllProps() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsedProps> getById(@PathVariable int id) {
		UsedProps f = service.getOneById(id);
		return new ResponseEntity<UsedProps>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsedProps> addProps(UsedProps f) {
		service.addNew(f);
		return new ResponseEntity<UsedProps>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsedProps> deleteProps(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
