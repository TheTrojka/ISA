package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Discounted;
import com.booking.springboot.web.repository.DiscountRepository;


@Service
public class DiscountService {

	@Autowired
	private DiscountRepository discountRepo;
	
	public DiscountService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Discounted> getAll(){
		ArrayList<Discounted> firme = new ArrayList<>();
		discountRepo.findAll().forEach(firme::add);
		return firme;
	}	
	
	public Discounted getOneById(int id){
		return discountRepo.findOne(id);
	}
	
	public Discounted addNew(Discounted sto){
		return discountRepo.save(sto);
		}
	
	public Discounted editSeat(Discounted sto){
		return discountRepo.save(sto);
	}
	
	public void deleteSeat(int id){
		discountRepo.delete(id);
	}
}

