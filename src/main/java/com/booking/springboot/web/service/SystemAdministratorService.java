package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.booking.springboot.web.repository.SystemAdministratorRepo;

import com.booking.springboot.web.users.model.SystemAdministrator;


@Service
public class SystemAdministratorService {

	@Autowired
	private SystemAdministratorRepo afzr;
	
	
	public SystemAdministratorService() {
		
		
	}
	
	public ArrayList<SystemAdministrator> getAll() {
		ArrayList<SystemAdministrator> adminiSys = new ArrayList<>();
		afzr.findAll().forEach(adminiSys::add);
		return adminiSys;
	}
	
	public SystemAdministrator getOneById(int id) {
		return afzr.findOne(id);
	}
	
	public SystemAdministrator addNew(SystemAdministrator f) {
		return afzr.save(f);
	}
	
	public SystemAdministrator edit(SystemAdministrator f) {
		return afzr.save(f);
	}
	
	public void delete(int id) {
		afzr.delete(id);
	}
}
