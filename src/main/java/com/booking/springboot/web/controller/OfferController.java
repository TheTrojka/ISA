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

import com.booking.springboot.web.model.FanZone;
import com.booking.springboot.web.model.Offer;
import com.booking.springboot.web.model.Props;
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.service.FanZoneService;
import com.booking.springboot.web.service.OfferService;
import com.booking.springboot.web.service.PropsAdService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fanzone/propsAd/{propId}/offer")
public class OfferController {

	@Autowired
	OfferService service;
	
	@Autowired
	GuestService guestService;
	
	@Autowired
	PropsAdService propService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Offer> getAllOffers(@PathVariable int propId) {
		return service.getAll(propId);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offer> getById(@PathVariable int propId, @PathVariable int id) {
		Offer f = service.getOneById(propId, id);
		return new ResponseEntity<Offer>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/user", method = RequestMethod.GET)
	public String getUser(@PathVariable int propId, @PathVariable int id) {
		Offer f = service.getOneByRealId(propId, id);
		Guest guest = guestService.getOneById(f.getGuest().getId());
		String returnString = guest.getName() + "  " + guest.getLastname();
		return returnString;
	}

	@RequestMapping(value="/{id}", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> add(@PathVariable int propId, @PathVariable int id,
			@RequestBody String pricePar){
		Guest guest = guestService.getOneById(id);
		PropsAd prop = propService.getOneById(propId);
		double price = Double.parseDouble(pricePar);
		Offer offer = new Offer();
		offer.setGuest(guest);
		offer.setProp(prop);
		offer.setPrice(price);
		service.addNew(offer);
		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> deleteProps(@PathVariable int propId, @PathVariable int id){
		service.deleteOffer(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Offer> update(@PathVariable int propId, @PathVariable int id
			, @RequestBody String price){
		Offer offer = service.getOneById(propId, id);
		offer.setPrice(Double.parseDouble(price));
		service.editOffer(offer);
		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	}

	
	@RequestMapping(value="/accept/{offerId}", method = RequestMethod.POST)
	public ResponseEntity<PropsAd> buy(@PathVariable int propId, @PathVariable int offerId) {
		PropsAd f = propService.getOneById(propId);
		Offer offer = service.getOneByRealId(propId, offerId);
		Guest guest = guestService.getOneById(offer.getGuest().getId());
		guest.getPropsAd().add(f);
		guestService.editGuest(guest);
		f.setAccepted(true);
		propService.edit(f);
		return new ResponseEntity<PropsAd>(f, HttpStatus.OK);
	}

}
