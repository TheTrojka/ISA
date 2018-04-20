package com.booking.springboot.web.controller.student1;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.entities.student1.Reservation;
import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.TimingService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.service.student1.ReservationService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
@RequestMapping("guests/{guestId}/reservations")
public class ReservationController {
	@Autowired
	private ReservationService resServ;
	
	@Autowired
	private GuestService guestServ;
	
	@Autowired
	private TimingService timingServ;
	
	@Autowired
	private BookedService bookedServ;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Reservation> getAllReservations(@PathVariable int guestId){
		return resServ.getAllByGuest(guestId);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<Reservation> getById(@PathVariable int guestId, @PathVariable int id){
		Reservation reserv = resServ.getOneById(guestId, id);
		if (reserv == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reservation>(reserv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/timing/{timingId}/seat/{id}" , method = RequestMethod.GET)
	public ResponseEntity<String> add(@PathVariable int guestId, @PathVariable int timingId, @PathVariable int id){
		///System.out.println("in");
		Reservation res = new Reservation();
		Guest g = guestServ.getOneById(guestId);
		System.out.println(g.getLastname());
		res.setmGuest(g);
		res.setTiming(timingServ.getOneById(timingId));
		System.out.println(res.getTiming().getTime());
		g.getReservations().add(res);
		Reservation r = resServ.addNew(res);
		Booked booked = new Booked();
		Timing timingparameter = timingServ.getOneById(res.getTiming().getId());
		Set<Seat> seatList = timingparameter.getSegment().getSeats();
		seatList.forEach((seat) -> {
			  if (seat.getId() == id)
			  {
				  System.out.println(seat.getEstablishment().getAddress());
				  booked.setSeat(seat);
				  booked.setTiming(timingparameter);		
				  bookedServ.addNew(booked);
			  }
		});		
		if (r == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(r.toString(), HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> deleteReserv(@PathVariable int id){
		resServ.deleteReservation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> updateSegment(Reservation reservation, @PathVariable int guestId){
		Reservation s = resServ.editReservation(reservation);
		if (s != null){
			return new ResponseEntity<Reservation>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
