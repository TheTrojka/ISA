package com.in28minutes.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Seat;
import com.in28minutes.springboot.web.repository.SeatRepository;


@Service
public class SeatService {

	@Autowired
	private SeatRepository stoRepo;
	
	public SeatService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Seat> getAll(int restoranId, int segmentId){
		ArrayList<Seat> stolovi = new ArrayList<>();
		stoRepo.findBySegment_IdAndEstablishment_Id(restoranId, segmentId).forEach(stolovi::add);
		return stolovi;
	}	
	
	public Seat getOneById(int restoranId, int segmentId, int id){
		Seat s = stoRepo.findBySegment_IdAndEstablishment_IdAndId(restoranId, segmentId, id);
		return s;
	}
	
	public Seat addNew(Seat sto){
		return stoRepo.save(sto);
	}
	
	public Seat editSeat(Seat sto){
		return stoRepo.save(sto);
	}
	
	public void deleteSeat(int id){
		stoRepo.delete(id);
	}
}

