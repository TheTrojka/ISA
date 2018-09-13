package com.booking.springboot.web.controller;

import java.text.ParseException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Discounted;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.BookedService;
import com.booking.springboot.web.service.DiscountService;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/establishment")
public class EstablishmentController {

	@Autowired
	EstablishmentService service;

	@Autowired
	BookedService bService;

	@Autowired
	DiscountService dService;

	@Autowired
	GuestService gService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Establishment> getAllEstablishments() {
		return service.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Establishment> getById(@PathVariable int id) {
		Establishment f = service.getOneById(id);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/getByUser", method = RequestMethod.GET)
	public ResponseEntity<Establishment> getByUser(@PathVariable int id) {
		Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
		return new ResponseEntity<Establishment>(establishment, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> addEstablishment(@RequestBody Establishment f) {
		Establishment exists = service.getOneByName(f.getName());
		if (exists != null) {
			return new ResponseEntity<Establishment>(HttpStatus.BAD_REQUEST);
		}
		service.addNew(f);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> deleteEstablishment(@PathVariable int id) {
		Establishment f = service.getOneById(id);
		f.setActive(false);
		service.edit(f);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Establishment> edit(@RequestBody Establishment est) {
		Establishment exists = service.getOneByName(est.getName());
		Establishment e = service.getOneById(est.getId());
		if (exists != null && e != exists) {
			return new ResponseEntity<Establishment>(HttpStatus.BAD_REQUEST);
		}
		if (est.getName() != null) {
			e.setName(est.getName());
		}
		if (est.getAddress() != null) {
			e.setAddress(est.getAddress());
		}
		if (est.getDescription() != null) {
			e.setDescription(est.getDescription());
		}
		Establishment f = service.edit(e);
		return new ResponseEntity<Establishment>(f, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/visitReport", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> visits(@PathVariable int id, @RequestBody String[] dates) throws ParseException {
		String from = dates[0];
		String to = dates[1];
		Integer visits = 0;
		System.out.println(to);
		System.out.println(from);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fromDate = format1.parse(from);
		Date toDate = format1.parse(to);
		ArrayList<Booked> allBookings = bService.getAll();
		ArrayList<Discounted> allDiscounts = dService.getAll();
		Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
		Set<Happening> happenings = establishment.getHappenings();
		for (Happening happening : happenings) {
			Set<Timing> timings = happening.getTimings();
			for (Timing timing : timings) {
				if (timing.getTime().before(toDate) && timing.getTime().after(fromDate)) {
					for (Booked booked : allBookings) {

						if (booked.getTiming().getId() == timing.getId()) {
							boolean foundInDiscounts = false;
							for (Discounted discount : allDiscounts) {

								if (discount.getBookedId().equals(booked)) {
									foundInDiscounts = true;
									if (discount.getTaken()) {
										visits = visits + 1;
									}
								}

							}
							if (!foundInDiscounts) {
								visits = visits + 1;
							}
						}

					}
				}
			}
		}
		return new ResponseEntity<Integer>(visits, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/visitDaily", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> visitsDaily(@PathVariable int id, @RequestBody String date) throws ParseException, JSONException {
		String from = date;
		from = from.substring(1, from.length()-1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date toDate = format1.parse(from);
		JSONArray visitPoints = new JSONArray();
		Integer visits = 0;
		for (int i = 0; i < 7; i++) 
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(toDate);
			calendar.add(Calendar.DATE, -1);
			Date fromDate = calendar.getTime();
			ArrayList<Booked> allBookings = bService.getAll();
			ArrayList<Discounted> allDiscounts = dService.getAll();
			Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
			Set<Happening> happenings = establishment.getHappenings();
			for (Happening happening : happenings) {
				Set<Timing> timings = happening.getTimings();
				for (Timing timing : timings) {
					calendar.setTime(timing.getTime());
					Date compare = calendar.getTime();
					if (compare.before(toDate) && compare.after(fromDate)) {
						for (Booked booked : allBookings) {

							if (booked.getTiming().getId() == timing.getId()) {
								boolean foundInDiscounts = false;
								for (Discounted discount : allDiscounts) {

									if (discount.getBookedId().equals(booked)) {
										foundInDiscounts = true;
										if (discount.getTaken()) {
											visits = visits + 1;
										}
									}

								}
								if (!foundInDiscounts) {
									visits = visits + 1;
								}
							}

						}
					}
				}
			}
			JSONObject visitPoint = new JSONObject();
			visitPoint.put("date", toDate.toString());
			visitPoint.put("point", visits);
			visitPoints.put(visitPoint);
			visits = 0;
			toDate = fromDate;
		}
		String json = visitPoints.toString();
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/visitWeekly", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> visitsMonthly(@PathVariable int id, @RequestBody String date) throws ParseException, JSONException {
		String from = date;
		from = from.substring(1, from.length()-1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date toDate = format1.parse(from);
		JSONArray visitPoints = new JSONArray();
		Integer visits = 0;
		for (int i = 0; i < 4; i++) 
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(toDate);
			calendar.add(Calendar.WEEK_OF_MONTH, -1);
			Date fromDate = calendar.getTime();
			System.out.println(toDate.toString());
			System.out.println(fromDate.toString());
			ArrayList<Booked> allBookings = bService.getAll();
			ArrayList<Discounted> allDiscounts = dService.getAll();
			Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
			Set<Happening> happenings = establishment.getHappenings();
			for (Happening happening : happenings) {
				Set<Timing> timings = happening.getTimings();
				for (Timing timing : timings) {
					calendar.setTime(timing.getTime());
					Date compare = calendar.getTime();
					if (compare.before(toDate) && compare.after(fromDate)) {
						for (Booked booked : allBookings) {

							if (booked.getTiming().getId() == timing.getId()) {
								boolean foundInDiscounts = false;
								for (Discounted discount : allDiscounts) {

									if (discount.getBookedId().equals(booked)) {
										foundInDiscounts = true;
										if (discount.getTaken()) {
											visits = visits + 1;
										}
									}

								}
								if (!foundInDiscounts) {
									visits = visits + 1;
								}
							}

						}
					}
				}
			}
			JSONObject visitPoint = new JSONObject();
			visitPoint.put("date", toDate.toString());
			visitPoint.put("point", visits);
			visitPoints.put(visitPoint);
			visits = 0;
			toDate = fromDate;
		}
		String json = visitPoints.toString();
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/visitMonthly", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> visitMonthly(@PathVariable int id, @RequestBody String date) throws ParseException, JSONException {
		String from = date;
		from = from.substring(1, from.length()-1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date toDate = format1.parse(from);
		JSONArray visitPoints = new JSONArray();
		Integer visits = 0;
		for (int i = 0; i < 12; i++) 
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(toDate);
			calendar.add(Calendar.MONTH, -1);
			Date fromDate = calendar.getTime();
			System.out.println(toDate.toString());
			System.out.println(fromDate.toString());
			ArrayList<Booked> allBookings = bService.getAll();
			ArrayList<Discounted> allDiscounts = dService.getAll();
			Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
			Set<Happening> happenings = establishment.getHappenings();
			for (Happening happening : happenings) {
				Set<Timing> timings = happening.getTimings();
				for (Timing timing : timings) {
					calendar.setTime(timing.getTime());
					Date compare = calendar.getTime();
					if (compare.before(toDate) && compare.after(fromDate)) {
						for (Booked booked : allBookings) {

							if (booked.getTiming().getId() == timing.getId()) {
								boolean foundInDiscounts = false;
								for (Discounted discount : allDiscounts) {

									if (discount.getBookedId().equals(booked)) {
										foundInDiscounts = true;
										if (discount.getTaken()) {
											visits = visits + 1;
										}
									}

								}
								if (!foundInDiscounts) {
									visits = visits + 1;
								}
							}

						}
					}
				}
			}
			JSONObject visitPoint = new JSONObject();
			visitPoint.put("date", toDate.toString());
			visitPoint.put("point", visits);
			visitPoints.put(visitPoint);
			visits = 0;
			toDate = fromDate;
		}
		String json = visitPoints.toString();
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/profitReport", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> profits(@PathVariable int id, @RequestBody String[] dates) throws ParseException {
		String from = dates[0];
		String to = dates[1];
		double profits = 0;
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fromDate = format1.parse(from);
		Date toDate = format1.parse(to);
		ArrayList<Booked> allBookings = bService.getAll();
		ArrayList<Discounted> allDiscounts = dService.getAll();
		Establishment establishment = service.getOneById(gService.getOneById(id).getEstablishment().getId());
		Set<Happening> happenings = establishment.getHappenings();
		for (Happening happening : happenings) {
			Set<Timing> timings = happening.getTimings();
			for (Timing timing : timings) {
				if (timing.getTime().before(toDate) && timing.getTime().after(fromDate)) {
					for (Booked booked : allBookings) {

						if (booked.getTiming().getId() == timing.getId()) {
							boolean foundInDiscounts = false;
							for (Discounted discount : allDiscounts) {

								if (discount.getBookedId().equals(booked)) {
									foundInDiscounts = true;
									if (discount.getTaken()) {
										profits = profits + happening.getPrice()
												- (happening.getPrice() / 100 * discount.getDiscountPercentage());
									}
								}

							}
							if (!foundInDiscounts) {
								profits = profits + happening.getPrice();
							}
						}

					}
				}
			}
		}
		return new ResponseEntity<Double>(profits, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/isAdmin/{user}", method = RequestMethod.GET)
	public ResponseEntity<Establishment> isAdmin(@PathVariable int id, @PathVariable int user) {
		Establishment est = service.getOneById(id);
		if (est.getAdmins().contains(gService.getOneById(user))) {
			return new ResponseEntity<Establishment>(est, HttpStatus.OK);
		} else {
			return new ResponseEntity<Establishment>(HttpStatus.BAD_REQUEST);
		}

	}
	/*
	 * @RequestMapping(value="/login", method=RequestMethod.PUT, consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Firma>
	 * logIn(@RequestBody Firma firma) { Firma temp = this.fs.logIn(firma);
	 * if(temp!=null && firma.getPassword() != null &&
	 * temp.getPassword().equals(firma.getPassword())){
	 * session.setAttribute("banka", temp); return new
	 * ResponseEntity<Firma>(temp,HttpStatus.OK); } return new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * /*@RequestMapping(value="/establishment") public String
	 * getAllEstablishments(ModelMap model) { ArrayList<Establishment> list =
	 * service.getAll(); model.addAttribute("list",list); return "list-todos"; }
	 * 
	 * @RequestMapping(value="/establishment/{id}", method = RequestMethod.GET)
	 * public String getById(@PathVariable int id,ModelMap model) { Establishment f
	 * = service.getOneById(id); model.addAttribute("f",f); return "establishment";
	 * }
	 * 
	 * @RequestMapping(value = "/establishment", method = RequestMethod.POST/*,
	 * consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public String
	 * addEstablishment(Establishment f,ModelMap model) { service.addNew(f);
	 * ArrayList<Establishment> list = service.getAll();
	 * model.addAttribute("list",list); return "list-todos"; }
	 * 
	 * @RequestMapping(value = "/establishment/addEstablishment") public String
	 * openEstablishmentAddForm() { return "addEstablishment"; }
	 * 
	 * @RequestMapping(value = "/establishment/editEstablishment/{id}") public
	 * String openEstablishmentEditForm(@PathVariable int id, ModelMap model) {
	 * Establishment f = service.getOneById(id); model.addAttribute("f",f); return
	 * "editEstablishment"; }
	 * 
	 * @RequestMapping(value = "/establishment/{id}", method =
	 * RequestMethod.DELETE/*, consumes =
	 * MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public void
	 * deleteEstablishment(@PathVariable int id){ service.delete(id); }
	 * 
	 * @RequestMapping(value = "/establishment", method = RequestMethod.PUT/*,
	 * consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public String edit(Establishment
	 * Establishment) { service.edit(Establishment); System.out.println("lol");
	 * return "list-todos"; }
	 */
}