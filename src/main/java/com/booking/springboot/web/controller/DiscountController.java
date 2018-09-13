package com.booking.springboot.web.controller;

import java.util.Date;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

import com.booking.springboot.web.entities.student1.Reservation;
import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Discounted;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.users.student1.Guest;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.DiscountService;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.TimingService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.service.student1.ReservationService;


@RestController
public class DiscountController {
	
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
	
	@Autowired
	DiscountService dService;
	
	@Autowired
	GuestService gService;
	
	@Autowired
	ReservationService rService;
	
	@RequestMapping(value = "/establishment/{establishmentId}/happening/{happeningId}/timing/{timingId}/discount/{id}/{percentage}",method = RequestMethod.POST)
	public ResponseEntity<Discounted> book(@PathVariable int establishmentId, @PathVariable int happeningId,
		@PathVariable int timingId, @PathVariable int id, @PathVariable int percentage){
		Booked booked = new Booked();
		Timing timingparameter = tService.getOneById(timingId);
		ArrayList<Seat> segmentSeats = new ArrayList<Seat>();  
		for(Segment segment : timingparameter.getSegment()) {
			segmentSeats.addAll(segment.getSeats());
		}
		segmentSeats.forEach((seat) -> {
			  if (seat.getId() == id)
			  {
				  booked.setSeat(seat);
			  }
		});
		
		booked.setTiming(timingparameter);		
		bService.addNew(booked);
		Discounted discount = new Discounted();
		discount.setBookedId(booked);
		discount.setDiscountPercentage(percentage);
		dService.addNew(discount);
		return new ResponseEntity<Discounted>(discount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/discount/{id}/take/{guestId}",method = RequestMethod.POST)
	public ResponseEntity<Discounted> taken(@PathVariable int id, @PathVariable int guestId){		
		Discounted discount = dService.getOneById(id);
		Guest guest = gService.getOneById(guestId);
		Timing timing = discount.getBookedId().getTiming();
		Reservation exist = rService.getOneByTimingAndSeat(timing.getId(),
				discount.getBookedId().getSeat().getId());
		if (exist != null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		Reservation reservation = new Reservation();
		reservation.setmGuest(guest);
		reservation.setTiming(timing);
		reservation.setSeat(discount.getBookedId().getSeat().getId());
		rService.addNew(reservation);
		discount.setTaken(true);
	    dService.editSeat(discount);
		return new ResponseEntity<Discounted>(discount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/establishment/{establishmentId}/discounted",method = RequestMethod.GET)
	public ResponseEntity<String> estDiscounts(@PathVariable int establishmentId) throws JSONException{		
		ArrayList<Discounted> discounts = dService.getAll();
		JSONArray discountInfos = new JSONArray();
		for(Discounted discount : discounts)
		{
			if(discount.getBookedId().getTiming().getSegment().stream().findFirst().get().getHall()
					.getEstablishment().getId() == establishmentId && 
					!discount.getTaken())
			{
				JSONObject discountInfo = new JSONObject();
				Timing timing = discount.getBookedId().getTiming();
				Happening happening = new Happening();
				for (Happening h : service.getAll())
				{
					if(h.getTimings().contains(timing))
					{
						happening = h;
					}
				}
				discountInfo.put("id", discount.getId());
				discountInfo.put("happening", happening.getTitle());
				discountInfo.put("time", timing.getTime());
				discountInfo.put("hall", timing.getSegment().stream().findFirst().get().getName());
				discountInfo.put("segment", discount.getBookedId().getSeat().getSegment().getName());
				discountInfo.put("seat", discount.getBookedId().getSeat().getId());
				discountInfo.put("price", happening.getPrice());
				discountInfo.put("percentage", discount.getDiscountPercentage());
				discountInfos.put(discountInfo);
			}
		}
		String json = discountInfos.toString();
		return new ResponseEntity<String>(json, HttpStatus.OK);
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
