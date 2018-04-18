package com.booking.springboot.web.service;

import java.util.ArrayList;
import java.util.Date; 
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.repository.TimingRepository;

@Service
public class TimingService {

	@Autowired
	private TimingRepository er;
	
	public TimingService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Timing> getAll() {
		ArrayList<Timing> firme = new ArrayList<>();
		er.findAll().forEach(firme::add);
		return firme;
	}
	
	public Timing getOneById(int id) {
		return er.findOne(id);
	}
	
	public Timing addNew(Timing f) {
		return er.save(f);
	}
	
	public Timing edit(Timing f) {
		return er.save(f);
	}
	
	public void delete(int id) {
		er.delete(id);
	}
}

