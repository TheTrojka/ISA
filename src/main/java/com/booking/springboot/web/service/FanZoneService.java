package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.booking.springboot.web.model.FanZone;
import com.booking.springboot.web.repository.FanZoneRepo;

@Service
public class FanZoneService {
	
	@Autowired
	private FanZoneRepo fzr;
	

	public FanZoneService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<FanZone> getAll() {
		ArrayList<FanZone> fanZone = new ArrayList<>();
		fzr.findAll().forEach(fanZone::add);
		return fanZone;
	}
	
	public FanZone getOneById(int id) {
		return fzr.findOne(id);
	}
	
	public FanZone addNew(FanZone f) {
		return fzr.save(f);
	}
	
	public FanZone edit(FanZone f) {
		return fzr.save(f);
	}
	
	public void delete(int id) {
		fzr.delete(id);
	}


}
