package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Hall;
import com.booking.springboot.web.repository.HallRepository;


@Service
public class HallService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HallRepository hallRepository;
	
	public HallService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Hall> getAll(int restoranId){
		ArrayList<Hall> halli = new ArrayList<>();
		hallRepository.findByEstablishment_Id(restoranId).forEach(halli::add);
		return halli;
	}
	
	public Hall getOneById(int restoranId, int id){
		Hall s = hallRepository.findByEstablishment_IdAndId(restoranId, id);
		return s;
	}
	
	public Hall addNew(Hall hall){
		logger.info("> addNew hall: ", hall);
		return hallRepository.save(hall);
	}
	
	public Hall editHall(Hall hall){
		return hallRepository.save(hall);
	}
	
	public void deleteHall(int id){
		hallRepository.delete(id);
	}

	public Hall getOneByEstablishmentAndName(int establishmentId, String name) {
		Hall s = hallRepository.findByEstablishment_IdAndName(establishmentId, name);
		return s;
	}

}

