package com.booking.springboot.web.controller.student1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.entities.student1.Reservation;
import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.EmailService;
import com.booking.springboot.web.service.TimingService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.service.student1.ReservationService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
public class ReservationController {
	@Autowired
	private ReservationService resServ;

	@Autowired
	private GuestService guestServ;

	@Autowired
	private TimingService timingServ;

	@Autowired
	private BookedService bookedServ;

	@Autowired
	private HappeningService happeningServ;

	@Autowired
	private EmailService emailService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Reservation> getAllReservations(@PathVariable int guestId) {
		return resServ.getAllByGuest(guestId);
	}

	@RequestMapping(value = "guests/{guestId}/reservations/visits", method = RequestMethod.GET)
	public ResponseEntity<String> getById(@PathVariable int guestId) throws JSONException {
		JSONArray visits = new JSONArray();
		ArrayList<Reservation> reserv = resServ.getAllByGuest(guestId);
		if (reserv == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			for (Reservation res : reserv) {
				JSONObject visit = new JSONObject();
				Timing timing = res.getTiming();
				visit.put("id", res.getId());
				visit.put("date", timing.getTime());
				visit.put("tid", Integer.toString(timing.getId()));
				ArrayList<Happening> list = happeningServ.getAll();
				for (Happening happening : list) {
					if (happening.getTimings().contains(timing)) {
						visit.put("name", happening.getTitle());
						visit.put("hid", Integer.toString(happening.getId()));
						boolean hrated = happening.getRatings().stream()
								.anyMatch(t -> t.getGuest() == guestServ.getOneById(guestId));
						visit.put("hrated", hrated);
						visit.put("establishment", happening.getEstablishment().getName());
						visit.put("eid", Integer.toString(happening.getEstablishment().getId()));
						boolean erated = happening.getEstablishment().getRatings().stream()
								.anyMatch(t -> t.getGuest() == guestServ.getOneById(guestId));
						visit.put("erated", erated);
						visits.put(visit);
					}
				}
			}
			String json = visits.toString();
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/visits/{resId}/invite/{guestId}", method = RequestMethod.POST)
	public ResponseEntity<String> invite(@PathVariable int resId, @PathVariable int guestId) {
		ArrayList<Guest> list = guestServ.getAll();
		Guest temp = new Guest();
		for (Guest g:list)
		{
			
			if(g.getRole() != null && !g.getRole().toString().equals("user")) 
			{
				temp = g;
				break;
			}
		}
		Reservation reservation = resServ.getOneByRealId(resId);
		reservation.setmGuest(temp);
		resServ.editReservation(reservation);
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo("arminaa78@gmail.com");
		registrationEmail.setSubject("Happening invitation");
		registrationEmail.setText("You have recieved an invitation to an event, click the link below to"
				+ " confirm or decline the invitation:\n" + "http://localhost:4200" + "/visits/" + resId + "/invite/"
				+ guestId + "/decide");
		registrationEmail.setFrom("noreply@domain.com");

		emailService.sendEmail(registrationEmail);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visits/{resId}/invite/{guestId}/accept", method = RequestMethod.POST)
	public ResponseEntity<String> acceptInvitation(@PathVariable int resId, @PathVariable int guestId) {
		Reservation reservation = resServ.getOneByRealId(resId);
		reservation.setmGuest(guestServ.getOneById(guestId));
		resServ.editReservation(reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visits/{resId}/invite/{guestId}/decline", method = RequestMethod.POST)
	public ResponseEntity<String> declineInvitation(@PathVariable int resId, @PathVariable int guestId) {
		Reservation reservation = resServ.getOneByRealId(resId);
		int seat = reservation.getSeat();
		Timing timing = reservation.getTiming();
		Booked booked = bookedServ.getOneBySeat_IdAndTiming_Id(seat,
				timing.getId());
		resServ.deleteReservation(resId);
		bookedServ.deleteSeat(booked.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visits/{resId}/cancel", method = RequestMethod.POST)
	public ResponseEntity<String> cancelReservation(@PathVariable int resId) {
		Reservation reservation = resServ.getOneByRealId(resId);
		int seat = reservation.getSeat();
		Timing timing = reservation.getTiming();
		Booked booked = bookedServ.getOneBySeat_IdAndTiming_Id(seat,
				timing.getId());
		resServ.deleteReservation(resId);
		bookedServ.deleteSeat(booked.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visits/{resId}/invite/{guestId}/invitation", method = RequestMethod.GET)
	public String invitation(@PathVariable int resId, @PathVariable int guestId) {
		Reservation reservation = resServ.getOneByRealId(resId);
		Guest guest = reservation.getmGuest();
		Timing timing = reservation.getTiming();
		String date = timing.getTime().toString();
		String happ = "";
		String price = "";
		String establishment = "";
		ArrayList<Happening> list = happeningServ.getAll();
		for (Happening happening : list) {
			if (happening.getTimings().contains(timing)) {
				happ = happening.getTitle();
				price = Double.toString(happening.getPrice());
				establishment = happening.getEstablishment().getName();
			}
		}
		String returnString = guest.getName() + " " + guest.getLastname() + " has invited you to an"
				+ " event. Details\r\n"
				+ "Establishment: " + establishment +"\r\n"
				+ "Happening: " + happ +"\r\n"
				+ "Date: " + date +"\r\n"
				+ "Price: " + price +"\r\n";
		return returnString;
	}

	@RequestMapping(value = "guests/{guestId}/reservations/timing/{timingId}/seat/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> add(@PathVariable int guestId, @PathVariable int timingId, @PathVariable int id) {
		/// System.out.println("in");
		Reservation res = new Reservation();
		Guest g = guestServ.getOneById(guestId);
		g.setResNum(g.getResNum()+1);
		Reservation exist = resServ.getOneByTimingAndSeat(timingId, id);
		if (exist != null) {
			System.out.println(exist.getId()+ "geg");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		res.setmGuest(g);
		res.setTiming(timingServ.getOneById(timingId));
		res.setSeat(id);
		System.out.println(res.getTiming().getTime());
		//g.getReservations().add(res);
		guestServ.editGuest(g);
		Reservation r = resServ.addNew(res);
		Booked booked = new Booked();
		Timing timingparameter = timingServ.getOneById(res.getTiming().getId());
		ArrayList<Seat> seatList = new ArrayList<Seat>();  
		for(Segment segment : timingparameter.getSegment()) {
			seatList.addAll(segment.getSeats());
		}
		seatList.forEach((seat) -> {
			if (seat.getId() == id) {
				System.out.println(seat.getEstablishment().getAddress());
				booked.setSeat(seat);
				booked.setTiming(timingparameter);
				bookedServ.addNew(booked);
			}
		});		
		return new ResponseEntity<String>(r.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "guests/{guestId}/reservations/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> deleteReserv(@PathVariable int id) {
		resServ.deleteReservation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "guests/{guestId}/reservations/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> updateSegment(Reservation reservation, @PathVariable int guestId) {
		Reservation s = resServ.editReservation(reservation);
		if (s != null) {
			return new ResponseEntity<Reservation>(s, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
