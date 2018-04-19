package com.in28minutes.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.users.model.AdministratorFanZone;
import com.in28minutes.springboot.web.model.Establishment;
import com.in28minutes.springboot.web.repository.AdministratorFanZoneRepo;

@Service
public class AdministratorFanZoneService {
	
	@Autowired
	private AdministratorFanZoneRepo afzr;
	
	
	public AdministratorFanZoneService() {
		
		
	}
	
	public ArrayList<AdministratorFanZone> getAll() {
		ArrayList<AdministratorFanZone> adminiFan = new ArrayList<>();
		afzr.findAll().forEach(adminiFan::add);
		return adminiFan;
	}
	
	public AdministratorFanZone getOneById(int id) {
		return afzr.findOne(id);
	}
	
	public AdministratorFanZone addNew(AdministratorFanZone f) {
		return afzr.save(f);
	}
	
	public AdministratorFanZone edit(AdministratorFanZone f) {
		return afzr.save(f);
	}
	
	public void delete(int id) {
		afzr.delete(id);
	}

}
