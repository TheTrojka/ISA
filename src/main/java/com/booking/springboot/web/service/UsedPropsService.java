package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import com.booking.springboot.web.model.UsedProps;
import com.booking.springboot.web.repository.UsedPropsRepo;

@Service
public class UsedPropsService {
	
	@Autowired
	private UsedPropsRepo upr;
	
	public UsedPropsService() {
		
		
	}
	
	public ArrayList<UsedProps> getAll() {
		ArrayList<UsedProps> usedprops = new ArrayList<>();
		upr.findAll().forEach(usedprops::add);
		return usedprops;
	}
	
	public UsedProps getOneById(int id) {
		return upr.findOne(id);
	}
	
	public UsedProps addNew(UsedProps f) {
		return upr.save(f);
	}
	
	public UsedProps edit(UsedProps f) {
		return upr.save(f);
	}
	
	public void delete(int id) {
		upr.delete(id);
	}


}
