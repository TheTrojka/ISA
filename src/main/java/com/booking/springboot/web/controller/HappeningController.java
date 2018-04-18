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

import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.HappeningService;
import com.booking.springboot.web.service.SegmentService;
import com.booking.springboot.web.service.TimingService;


@RestController
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
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Happening> getSveStavke(@PathVariable int establishmentId) {
		return service.getAllByEstablishment(establishmentId);
	}
	
	@RequestMapping(value = "/{id}",
				method = RequestMethod.GET)
	public ResponseEntity<Happening> getById(@PathVariable int establishmentId, @PathVariable int id) {
		Happening fs = service.getOneById(establishmentId, id);
		return new ResponseEntity<Happening>(fs, HttpStatus.OK); 
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> add(@RequestBody Happening fs, @PathVariable int establishmentId){
		Happening faks = fs;
		System.out.println("no");
		Establishment f = eService.getOneById(establishmentId);
		faks.setEstablishment(f);
		f.getHappenings().add(faks);
		Happening fakts = service.addNew(faks);
		return new ResponseEntity<Happening>(fakts, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> update(@RequestBody Happening fs, @PathVariable int establishmentId){
		Happening faks = fs;
		System.out.println("na");
		Establishment f = eService.getOneById(establishmentId);
		faks.setEstablishment(f);
		f.getHappenings().add(faks);
		Happening fakts = service.addNew(faks);
		return new ResponseEntity<Happening>(fakts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Happening> deleteStavka(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{happeningId}/segment/{id}",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> addToHappening(@RequestBody Segment segment,@PathVariable int establishmentId, @PathVariable int happeningId
			,@PathVariable int id){
		Segment seg = sService.getOneById(establishmentId, id);
		Happening r = service.getOneById(establishmentId, happeningId);
		r.getSegments().add(seg);
		service.edit(r);
		return new ResponseEntity<Happening>(r, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{happeningId}/segment/{id}/timing",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> addToHappening(@PathVariable int establishmentId, @PathVariable int happeningId,
		@PathVariable int id, @RequestBody String time){
		System.out.println(time);
		time = time.substring(1, time.length()-1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Timing timing = new Timing();
		try {
			timing.setTime(format1.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Segment segment = sService.getOneById(establishmentId, id);
		timing.setSegment(segment);
		tService.addNew(timing);
		Happening r = service.getOneById(establishmentId, happeningId);
		r.getTimings().add(timing);
		service.edit(r);
		return new ResponseEntity<Happening>(r, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}/timing",
			method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ArrayList<Timing>> getTimings(@PathVariable int establishmentId, @PathVariable int id) {
		Happening fs = service.getOneById(establishmentId, id);
		Set<Timing> timings = fs.getTimings();
		System.out.println(timings.size());
		ArrayList<Timing> timingList = new ArrayList<Timing>();
		timingList.addAll(timings);
		System.out.println(timingList.size());
		return new ResponseEntity<ArrayList<Timing>>(timingList, HttpStatus.OK); 
	}
	
	
	
	/*@RequestMapping(value="/establishment/{id}/happening/{hid}", method = RequestMethod.GET)
	public String getById(@PathVariable("hid") int id,ModelMap model) {
		Happening f = service.getOneById(id);
		model.addAttribute("f",f);
		return "happening";
	}
	
	
	
	@RequestMapping(value = "/establishment/{id}/happening/addHappening")
	public String openEstablishmentAddForm(@PathVariable int id,ModelMap model) {
		model.addAttribute("list",list1);
		return "addHappening";
	}
	
	*/

}
