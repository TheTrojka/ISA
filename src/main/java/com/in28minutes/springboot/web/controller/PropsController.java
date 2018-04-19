package com.in28minutes.springboot.web.controller;

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





import com.in28minutes.springboot.web.model.FanZone;
import com.in28minutes.springboot.web.model.Props;
import com.in28minutes.springboot.web.model.PropsAd;
import com.in28minutes.springboot.web.service.FanZoneService;
import com.in28minutes.springboot.web.service.PropsService;

@RestController
@RequestMapping("/fanzone/{fanzoneId}/props")
public class PropsController {
	
	@Autowired
	PropsService service;
	
	@Autowired
	FanZoneService fService;
	
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
	public ResponseEntity<Props> add(@RequestBody Props pr, @PathVariable int fanzoneId){
		Props props = pr;
		System.out.println("no");
		FanZone f = fService.getOneById(fanzoneId);
		props.setFanzone(f);
		f.getProps().add(props);
		Props propss = service.addNew(props);
		return new ResponseEntity<Props>(propss, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Props> deleteProps(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Props> update(@RequestBody Props pr, @PathVariable int fanzoneId){
		Props props = pr;
		System.out.println("na");
		FanZone f = fService.getOneById(fanzoneId);
		props.setFanzone(f);
		f.getProps().add(props);
		Props propss = service.addNew(props);
		return new ResponseEntity<Props>(propss, HttpStatus.OK);
	}



}
