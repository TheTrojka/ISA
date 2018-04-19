package com.booking.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;







import com.booking.springboot.web.model.FanZone;
import com.booking.springboot.web.service.FanZoneService;

@RestController
@RequestMapping("/fanzone")
public abstract class FanZoneController {
	
	    @Autowired
	    FanZoneService service;
	    
	    @RequestMapping(value="/{id}", method = RequestMethod.GET)
		public ResponseEntity<FanZone> getById(@PathVariable int id) {
	    	FanZone f = service.getOneById(id);
			return new ResponseEntity<FanZone>(f, HttpStatus.OK);
		}

		@RequestMapping(method = RequestMethod.POST,
				consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<FanZone> addFanZone(FanZone f) {
			service.addNew(f);
			return new ResponseEntity<FanZone>(f, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/{id}",
				method = RequestMethod.DELETE,
				consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<FanZone> deleteFanZone(@PathVariable int id){
			service.delete(id);	
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@RequestMapping(
				method = RequestMethod.PUT,
				consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<FanZone> edit(FanZone firma) {
			FanZone f = service.edit(firma);
			return new ResponseEntity<FanZone>(f, HttpStatus.OK);
		}
	    

}
