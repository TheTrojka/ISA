package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Props;
import com.booking.springboot.web.repository.PropsRepo;


@Service
public class PropsService {
	
	@Autowired
	private PropsRepo pr;
	
	public PropsService() {
		
		
	}
	
	public ArrayList<Props> getAll() {
		ArrayList<Props> props = new ArrayList<>();
		pr.findAll().forEach(props::add);
		return props;
	}
	
	public Props getOneById(int id) {
		return pr.findOne(id);
	}
	
	public Props addNew(Props f) {
		return pr.save(f);
	}
	
	public Props edit(Props f) {
		return pr.save(f);
	}
	
	public void delete(int id) {
		pr.delete(id);
	}

	public Props getOneByName(String name) {
		// TODO Auto-generated method stub
		return pr.getOneByName(name);
	}

}
