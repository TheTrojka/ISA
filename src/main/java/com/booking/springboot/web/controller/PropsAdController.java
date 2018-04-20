package com.booking.springboot.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@RequestMapping("/fanzone/propsAd")
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
	public ResponseEntity<PropsAd> add(@RequestBody PropsAd fs){
		
		System.out.println(fs.getDate().toString());
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		PropsAd propsAd = new PropsAd();
		try {
		    propsAd.setDate(format1.parse(fs.getDate().toString()));
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		service.addNew(fs);
		return new ResponseEntity<PropsAd>(fs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> deleteProps(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> update(@RequestBody PropsAd fs){
		
		PropsAd fakts = service.edit(fs);
		return new ResponseEntity<PropsAd>(fakts, HttpStatus.OK);
	}

}
