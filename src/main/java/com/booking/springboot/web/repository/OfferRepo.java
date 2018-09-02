package com.booking.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Offer;
import com.booking.springboot.web.model.Seat;

public interface OfferRepo extends CrudRepository<Offer, Integer>{
	
	public ArrayList<Offer> findByProp_Id(int propId); // jpa sam izvali sta se trazi iz naziva metode: select s from segment where maticniRestoran=?
	public Offer findByProp_IdAndGuest_Id(int propId, int guestId);
	public Offer findByProp_IdAndId(int propId, int id);
}
