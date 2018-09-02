package com.booking.springboot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Rating;
import com.booking.springboot.web.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	
	public RatingService() {
		// TODO Auto-generated constructor stub
	}
	
	public Iterable<Rating> getAll(){
		return ratingRepo.findAll();
	}	
	
	public Rating getOneById(int id){
		return ratingRepo.findOne(id);
	}
	
	public Rating addNew(Rating sto){
		return ratingRepo.save(sto);
	}
	
	public Rating editRating(Rating sto){
		return ratingRepo.save(sto);
	}
	
	public void deleteRating(int id){
		ratingRepo.delete(id);
	}
}
