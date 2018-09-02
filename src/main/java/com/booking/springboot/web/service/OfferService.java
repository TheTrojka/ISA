package com.booking.springboot.web.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.model.Offer;
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.repository.OfferRepo;

@Service
public class OfferService {

	@Autowired
	private OfferRepo offerRepo;
	
	public OfferService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Offer> getAll(int propId){
		ArrayList<Offer> offers = new ArrayList<>();
		offerRepo.findByProp_Id(propId).forEach(offers::add);
		return offers;
	}	
	
	public Offer getOneById(int propId, int guestId){
		return offerRepo.findByProp_IdAndGuest_Id(propId, guestId);
	}
	
	public Offer getOneByRealId(int propId, int id){
		return offerRepo.findByProp_IdAndId(propId, id);
	}
	
	public Offer addNew(Offer sto){
		return offerRepo.save(sto);
	}
	
	public Offer editOffer(Offer sto){
		return offerRepo.save(sto);
	}
	
	public void deleteOffer(int id){
		offerRepo.delete(id);
	}
}
