package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.repository.HappeningRepository;

@Service
public class HappeningService {
	
	@Autowired
	private HappeningRepository hr;
	
	public HappeningService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Happening> getAllByEstablishment(int fakturaId) {
		ArrayList<Happening> firme = new ArrayList<>();
		hr.findByEstablishment_Id(fakturaId).forEach(firme::add);
		return firme;
	}
	
	public Happening getOneById(int fakturaId, int id) {
		Happening fs = hr.findByEstablishment_IdAndId(fakturaId, id);
		return fs;
	}
	
	public Happening addNew(Happening f) {
		return hr.save(f);
	}
	
	public Happening edit(Happening f) {;
		return hr.save(f);
	}
	
	public void delete(int id) {
		hr.delete(id);
	}

	public Happening getAllByOneReservation(int reservId, int id) {
		Happening h = hr.findByEstablishment_IdAndId(reservId, id);
		return h;
	}

}
