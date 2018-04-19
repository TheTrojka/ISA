package com.in28minutes.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.PropsAd;
import com.in28minutes.springboot.web.repository.PropsAdRepo;

@Service
public class PropsAdService {

	@Autowired
	private PropsAdRepo par;
	
	public PropsAdService() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<PropsAd> getAll() {
		ArrayList<PropsAd> propsad = new ArrayList<>();
		par.findAll().forEach(propsad::add);
		return propsad;
	}
	
	public PropsAd getOneById(int id) {
		return par.findOne(id);
	}
	
	public PropsAd addNew(PropsAd f) {
		return par.save(f);
	}
	
	public PropsAd edit(PropsAd f) {
		return par.save(f);
	}
	
	public void delete(int id) {
		par.delete(id);
	}

}
