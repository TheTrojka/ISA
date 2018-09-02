package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Status;
import com.booking.springboot.web.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepo;
	
	public StatusService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Status> getAll(){
		ArrayList<Status> statuses = new ArrayList<>();
		statusRepo.findAll().forEach(statuses::add);
		return statuses;
	}	
	
	public Status getOneById(int id){
		return statusRepo.findOne(id);
	}
	
	
	public Status addNew(Status sto){
		return statusRepo.save(sto);
	}
	
	public Status editstatus(Status sto){
		return statusRepo.save(sto);
	}
	
	public void deletestatus(int id){
		statusRepo.delete(id);
	}
}


