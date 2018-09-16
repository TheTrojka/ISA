package com.booking.springboot.web.controller;

import java.util.Set;
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

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Hall;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.SeatService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.TimingService;
import com.booking.springboot.web.service.student1.GuestService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establishment/{establishmentId}/happening")
public class HappeningController {

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
	GuestService gService;
	
	@Autowired
	SeatService seatService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Happening> getSveStavke(@PathVariable int establishmentId) {
		return service.getAllByEstablishment(establishmentId);
	}

	@RequestMapping(value = "/byUser", method = RequestMethod.GET)
	public ArrayList<Happening> getHappeningsById(@PathVariable int establishmentId) {
		return service.getAllByEstablishment(gService.getOneById(establishmentId).getEstablishment().getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Happening> getById(@PathVariable int establishmentId, @PathVariable int id) {
		Happening fs = service.getOneById(establishmentId, id);
		return new ResponseEntity<Happening>(fs, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> add(@RequestBody Happening fs, @PathVariable int establishmentId) {
		Happening exists = service.getOneByName(fs.getTitle(), establishmentId);
		if (exists != null) {
			return new ResponseEntity<Happening>(HttpStatus.BAD_REQUEST);
		}
		Happening faks = fs;
		System.out.println("no");
		Establishment f = eService.getOneById(establishmentId);
		faks.setEstablishment(f);
		faks.setActive(true);
		f.getHappenings().add(faks);
		Happening fakts = service.addNew(faks);
		return new ResponseEntity<Happening>(fakts, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> update(@RequestBody Happening fs, @PathVariable int establishmentId) {
		Happening h = service.getOneById(fs.getId());
		Happening exists = service.getOneByName(fs.getTitle(), establishmentId);
		if (exists != null && h != exists) {
			return new ResponseEntity<Happening>(HttpStatus.BAD_REQUEST);
		}		
		if (fs.getTitle() != null) {
			h.setTitle(fs.getTitle());
		}
		if (fs.getActors() != null) {
			h.setActors(fs.getActors());
		}
		if (fs.getDecription() != null) {
			h.setDecription(fs.getDecription());
		}
		if (fs.getDirector() != null) {
			h.setDirector(fs.getDirector());
		}
		if (fs.getGenre() != null) {
			h.setGenre(fs.getGenre());
		}
		if (fs.getDuration() != 0) {
			h.setDuration(fs.getDuration());
		}
		if (fs.getPrice() != 0) {
			h.setPrice(fs.getPrice());
		}
		if (fs.getPicture() != null) {
			h.setPicture(fs.getPicture());
		}
		Happening f = service.edit(h);
		return new ResponseEntity<Happening>(f, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Happening> deleteStavka(@PathVariable int id) {
		Happening f = service.getOneById(id);
		f.setActive(false);
		service.edit(f);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/bookings", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Timing>> getHallBookings(@PathVariable int id) {
		Happening happening = service.getOneById(id);
		ArrayList<Timing> returnTimings = new ArrayList<Timing>();
		returnTimings.addAll(happening.getTimings());
		return new ResponseEntity<ArrayList<Timing>>(returnTimings, HttpStatus.OK);
	}

	@RequestMapping(value = "/{happeningId}/segment/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> addToHappening(@RequestBody Segment segment, @PathVariable int establishmentId,
			@PathVariable int happeningId, @PathVariable int id) {
		Segment seg = sService.getOneById(establishmentId, id);
		Happening r = service.getOneById(establishmentId, happeningId);
		r.getSegments().add(seg);
		service.edit(r);
		return new ResponseEntity<Happening>(r, HttpStatus.OK);
	}

	@RequestMapping(value = "/{happeningId}/timing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Timing> addToHappening(@PathVariable int establishmentId, @PathVariable int happeningId,
			@RequestBody String time) {
		System.out.println(time);
		time = time.substring(1, time.length() - 1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Timing timing = new Timing();
		try {
			timing.setTime(format1.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timing rett = tService.addNew(timing);
		Happening r = service.getOneById(establishmentId, happeningId);
		r.getTimings().add(timing);
		service.edit(r);
		return new ResponseEntity<Timing>(rett, HttpStatus.OK);
	}

	@RequestMapping(value = "/{happeningId}/timing/{tid}/segment/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Timing> addToHappening(@PathVariable int establishmentId, @PathVariable int happeningId,
			@PathVariable int tid, @PathVariable int id, @RequestBody String time) {
		System.out.println(id);
		Segment segment = sService.getOneById(id);
		Timing timing = tService.getOneById(tid);
		System.out.println(timing.getSegment().size());
		timing.getSegment().add(segment);
		System.out.println(timing.getSegment().size());
		for (Segment s : timing.getSegment()) {
			System.out.println(s.getName());
		}
		Timing rett = tService.addNew(timing);
		return new ResponseEntity<Timing>(rett, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/timing", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Timing>> getTimings(@PathVariable int establishmentId, @PathVariable int id) {
		Happening fs = service.getOneById(establishmentId, id);
		Set<Timing> timings = fs.getTimings();
		System.out.println(timings.size());
		ArrayList<Timing> timingList = new ArrayList<Timing>();
		timingList.addAll(timings);
		System.out.println(timingList.size());
		return new ResponseEntity<ArrayList<Timing>>(timingList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/timing/{timing}/free", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Seat>> getFreeSeats(@PathVariable int establishmentId, @PathVariable int id,
			@PathVariable int timing) {
		Happening fs = service.getOneById(establishmentId, id);
		Set<Timing> timings = fs.getTimings();
		Timing timPar = new Timing();
		for (Timing tim : timings) {
			if (tim.getId() == timing) {
				timPar = tim;
			}
		}
		Set<Segment> segments = timPar.getSegment();
		ArrayList<Seat> segmentSeats = new ArrayList<Seat>();
		for (Segment segment : segments) {
			segmentSeats.addAll(segment.getSeats());
		}
		ArrayList<Seat> freeSeats = new ArrayList<Seat>();
		ArrayList<Booked> allBookings = bService.getAll();
		for (Seat seto : segmentSeats) {
			boolean free = true;
			if (!seto.isActive()) {
				free = false;
			}
			for (Booked booked : allBookings) {
				if (booked.getTiming().getId() == timPar.getId() && booked.getSeat().getId() == seto.getId()) {
					free = false;
				}
			}
			if (free) {
				freeSeats.add(seto);
			}
		}
		return new ResponseEntity<ArrayList<Seat>>(freeSeats, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/timing/{timing}/hall", method = RequestMethod.GET)
	public ResponseEntity<Hall> getHall(@PathVariable int establishmentId, @PathVariable int id,
			@PathVariable int timing) {
		Timing timingpar = tService.getOneById(timing);
		Hall hall = timingpar.getSegment().stream().findFirst().get().getHall();
		return new ResponseEntity<Hall>(hall, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/timing/{timing}/segment", method = RequestMethod.GET)
	public ResponseEntity<Segment> getSegment(@PathVariable int establishmentId, @PathVariable int id,
			@PathVariable int timing) {
		Seat seat = seatService.getOneByRealId(timing);
		Segment segment = seat.getSegment();
		return new ResponseEntity<Segment>(segment, HttpStatus.OK);
	}

}
