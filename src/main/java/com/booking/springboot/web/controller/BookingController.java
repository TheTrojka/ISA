package com.booking.springboot.web.controller;

import java.util.Date;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.TimingService;


@RestController
@RequestMapping("/establishment/{establishmentId}/happening/{happeningId}/timing/{timingId}/book")
public class BookingController {
	
	@Autowired
    HappeningService service;
	
	@Autowired
	EstablishmentService eService;
	
	@Autowired
	SegmentService sService;
	
	@Autowired
	TimingService tService;
	
	@Autowired
	BookedService bService;
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Booked> book(@PathVariable int establishmentId, @PathVariable int happeningId,
		@PathVariable int timingId, @PathVariable int id){
		Booked booked = new Booked();
		Timing timingparameter = tService.getOneById(timingId);
		Set<Seat> seatList = timingparameter.getSegment().getSeats();
		seatList.forEach((seat) -> {
			  if (seat.getId() == id)
			  {
				  booked.setSeat(seat);
			  }
		});
		booked.setTiming(timingparameter);		
		bService.addNew(booked);
		return new ResponseEntity<Booked>(booked, HttpStatus.OK);
	}
	
	/*@RequestMapping(value="/establishment/{id}/happening/{hid}", method = RequestMethod.GET)
	public String getById(@PathVariable("hid") int id,ModelMap model) {
		Happening f = service.getOneById(id);
		model.addAttribute("f",f);
		return "happening";
	}
	
	@RequestMapping(value="/establishment/{id}", method = RequestMethod.GET)
	public String getList(@PathVariable int id,ModelMap model) {
		ArrayList<Happening> list  = service.getAll();
		ArrayList<Happening> list1 = new ArrayList<Happening>();
		for(Happening show:list) 
		{
			if(show.getEstablishment().getId()==id)
			{
				list1.add(show); 
			}
		}
		model.addAttribute("list",list1);
	    return "happening-list";
	}
	
	@RequestMapping(value = "/establishment/{id}/happening/addHappening")
	public String openEstablishmentAddForm(@PathVariable int id,ModelMap model) {
		model.addAttribute("list",list1);
		return "addHappening";
	}
	
	@RequestMapping(value = "/establishment/{id}/happening",
			method = RequestMethod.POST/*,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String addEstablishment(Happening f
			,@RequestParam("id") int id) {
		if(id==0) {
			service.addNew(f);			
		}else {
			f.setId(id);
			service.edit(f);			
		}
		return "redirect:establishment";
	}*/

}
