package com.booking.springboot.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.service.FanZoneService;
import com.booking.springboot.web.service.PropsAdService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fanzone/propsAd")
public class PropsAdController {

	@Autowired
	PropsAdService service;
	
	@Autowired
	FanZoneService fService;
	
	@Autowired
	GuestService guestService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<PropsAd> getAllProps() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<PropsAd> getById(@PathVariable int id) {
		PropsAd f = service.getOneById(id);
		return new ResponseEntity<PropsAd>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/reviewed", method = RequestMethod.POST)
	public ResponseEntity<PropsAd> reviewed(@PathVariable int id) {
		PropsAd f = service.getOneById(id);
		if(f == null || f.isReviewed())
		{
			return new ResponseEntity<PropsAd>(HttpStatus.BAD_REQUEST);
		}
		f.setReviewed(true);
		service.edit(f);
		return new ResponseEntity<PropsAd>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/seller/{seller}", method = RequestMethod.GET)
	public String getSeller(@PathVariable int id, @PathVariable int seller) {
		PropsAd f = service.getOneById(id);
		String returnString = "";
		if(f.getGuest().getId() == seller) {
			returnString = "exist";
		}
		return returnString;
	}

	@RequestMapping(value="/{seller}", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> add(@PathVariable int seller, @RequestBody PropsAd propsAd){
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String newDate = format1.format(propsAd.getDate());
		try {
		    propsAd.setDate(format1.parse(newDate));
		    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Guest guest = guestService.getOneById(seller);
		propsAd.setGuest(guest);
		propsAd.setAccepted(false);
		propsAd.setReviewed(false);
		service.addNew(propsAd);
		return new ResponseEntity<PropsAd>(propsAd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PropsAd> deleteProps(@PathVariable int id){
		PropsAd f = service.getOneById(id);
		if(f == null || f.isReviewed())
		{
			return new ResponseEntity<PropsAd>(HttpStatus.BAD_REQUEST);
		}
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
