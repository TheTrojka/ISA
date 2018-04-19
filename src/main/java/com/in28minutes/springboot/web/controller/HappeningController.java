package com.in28minutes.springboot.web.controller;

import java.util.Date;
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

import com.in28minutes.springboot.web.model.Establishment;
import com.in28minutes.springboot.web.model.Happening;
import com.in28minutes.springboot.web.model.Segment;
import com.in28minutes.springboot.web.model.Timing;
import com.in28minutes.springboot.web.service.EstablishmentService;
import com.in28minutes.springboot.web.service.HappeningService;
import com.in28minutes.springboot.web.service.SegmentService;
import com.in28minutes.springboot.web.service.TimingService;


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
	
	@RequestMapping(value = "/{happeningId}/timing",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Happening> addToHappening(@PathVariable int establishmentId, @PathVariable int happeningId,Timing fs){
		Timing seg = fs;
		tService.addNew(seg);
		Happening r = service.getOneById(establishmentId, happeningId);
		r.getTimings().add(seg);
		service.edit(r);
		return new ResponseEntity<Happening>(r, HttpStatus.OK);
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
