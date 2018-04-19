package com.booking.springboot.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;








import com.booking.springboot.web.model.FanZone;
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.service.FanZoneService;
import com.booking.springboot.web.service.PropsAdService;



@RestController
@RequestMapping("/fanzone/{fanzoneId}/propsAd")
public class PropsAdController {

	@Autowired
	PropsAdService service;
	
	@Autowired
	FanZoneService fService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<PropsAd> getAllProps() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<PropsAd> getById(@PathVariable int id) {
		PropsAd f = service.getOneById(id);
		return new ResponseEntity<PropsAd>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> add(@RequestBody PropsAd fs, @PathVariable int fanzoneId){
		PropsAd faks = fs;
		System.out.println("no");
		FanZone f = fService.getOneById(fanzoneId);
		faks.setFanzone(f);
		f.getPropsAd().add(faks);
		PropsAd fakts = service.addNew(faks);
		return new ResponseEntity<PropsAd>(fakts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> deleteProps(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> update(@RequestBody PropsAd fs, @PathVariable int fanzoneId){
		PropsAd faks = fs;
		System.out.println("na");
		FanZone f = fService.getOneById(fanzoneId);
		faks.setFanzone(f);
		f.getPropsAd().add(faks);
		PropsAd fakts = service.addNew(faks);
		return new ResponseEntity<PropsAd>(fakts, HttpStatus.OK);
	}

}
