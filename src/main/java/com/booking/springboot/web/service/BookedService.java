package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Booked;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Seat;
import com.booking.springboot.web.repository.BookedRepository;
import com.booking.springboot.web.repository.SeatRepository;


@Service
public class BookedService {

	@Autowired
	private BookedRepository bookRepo;
	
	public BookedService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Booked> getAll(){
		ArrayList<Booked> firme = new ArrayList<>();
		bookRepo.findAll().forEach(firme::add);
		return firme;
	}	
	
	public Booked getOneById(int id){
		return bookRepo.findOne(id);
	}
	
	public Booked getOneBySeat_IdAndTiming_Id(int seatId, int timingId){
		return bookRepo.getOneBySeat_IdAndTiming_Id(seatId, timingId);
	}
	
	public Booked addNew(Booked sto){
		return bookRepo.save(sto);
		}
	
	public Booked editSeat(Booked sto){
		return bookRepo.save(sto);
	}
	
	public void deleteSeat(int id){
		bookRepo.delete(id);
	}
}

