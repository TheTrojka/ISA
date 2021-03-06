package com.booking.springboot.web.service;

import java.util.ArrayList;
import java.util.Date; 
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.repository.EstablishmentRepository;

@Service
public class EstablishmentService {

	@Autowired
	private EstablishmentRepository er;
	
	public EstablishmentService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Establishment> getAll() {
		ArrayList<Establishment> firme = new ArrayList<>();
		er.findAll().forEach(firme::add);
		return firme;
	}
	
	public Establishment getOneById(int id) {
		return er.findOne(id);
	}
	
	public Establishment addNew(Establishment f) {
		return er.save(f);
	}
	
	public Establishment edit(Establishment f) {
		return er.save(f);
	}
	
	public void delete(int id) {
		er.delete(id);
	}

	public Establishment getOneByName(String name) {
		return er.findOneByName(name);
	}
}
