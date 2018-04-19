package com.booking.springboot.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.EstablishmentService;


@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService service;
    
    @Autowired
    BookedService bService;
    
    @RequestMapping(method = RequestMethod.GET)
	public ArrayList<Establishment> getAllEstablishments() {
		return service.getAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Establishment> getById(@PathVariable int id) {
		Establishment f = service.getOneById(id);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> addEstablishment(@RequestBody Establishment f) {
		Establishment est = new Establishment(f.getName(), f.getAddress());
		service.addNew(est);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> deleteEstablishment(@PathVariable int id){
		service.delete(id);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> edit(@RequestBody Establishment firma) {
		Establishment f = service.edit(firma);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/visitReport",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> addToHappening(@PathVariable int id, @RequestParam String from,
			@RequestParam String to) throws ParseException{
		Integer visits = 0;
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		Date fromDate = format1.parse(from);
		Date toDate = format1.parse(to);
		ArrayList<Booked> allBookings = bService.getAll();
		Establishment establishment = service.getOneById(id);
		Set<Happening> happenings = establishment.getHappenings();
		for(Happening happening : happenings) {
			  Set<Timing> timings = happening.getTimings();
			  for(Timing timing : timings) {
				  if (timing.getTime().before(toDate) || timing.getTime().after(fromDate))
				  {
					  for(Booked booked : allBookings) 
					  {
						  System.out.println(booked.getTiming().getId());
						  System.out.println(timing.getId());
						  if(booked.getTiming().getId() == timing.getId())
						  {
							  
							  visits = visits + 1;
						  }
					  }
				  }
			}
		}
		return new ResponseEntity<Integer>(visits, HttpStatus.OK);
	}
	/*
	@RequestMapping(value="/login",
			method=RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Firma> logIn(@RequestBody Firma firma) {
		Firma temp = this.fs.logIn(firma);
	if(temp!=null && firma.getPassword() != null && temp.getPassword().equals(firma.getPassword())){
		session.setAttribute("banka", temp);
		return new ResponseEntity<Firma>(temp,HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/*@RequestMapping(value="/establishment")
	public String getAllEstablishments(ModelMap model) {
		ArrayList<Establishment> list  = service.getAll();
		model.addAttribute("list",list);
	    return "list-todos";
	}
	
	@RequestMapping(value="/establishment/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable int id,ModelMap model) {
		Establishment f = service.getOneById(id);
		model.addAttribute("f",f);
		return "establishment";
	}
	
	@RequestMapping(value = "/establishment",
			method = RequestMethod.POST/*,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String addEstablishment(Establishment f,ModelMap model) {
		service.addNew(f);
		ArrayList<Establishment> list = service.getAll();
		model.addAttribute("list",list);
		return "list-todos";
	}
	
	@RequestMapping(value = "/establishment/addEstablishment")
	public String openEstablishmentAddForm() {
		return "addEstablishment";
	}
	
	@RequestMapping(value = "/establishment/editEstablishment/{id}")
	public String openEstablishmentEditForm(@PathVariable int id, ModelMap model) {
		Establishment f = service.getOneById(id);
		model.addAttribute("f",f);
		return "editEstablishment";
	}
	
	@RequestMapping(value = "/establishment/{id}",
			method = RequestMethod.DELETE/*,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEstablishment(@PathVariable int id){
		service.delete(id);		
	}
	
	@RequestMapping(value = "/establishment",
			method = RequestMethod.PUT/*,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String edit(Establishment Establishment) {
		service.edit(Establishment);
		System.out.println("lol");
		return "list-todos";
	}*/
}