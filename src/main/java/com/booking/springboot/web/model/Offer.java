package com.booking.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = false, nullable = false)
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="guest_id", nullable=false)
	@JsonBackReference(value="guest-offer")
    private Guest guest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prop_id", nullable=false)
	@JsonBackReference(value="prop-offer")
    private PropsAd prop;
	
	

    public Offer() {
		
		
	  }



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public Guest getGuest() {
		return guest;
	}



	public void setGuest(Guest guest) {
		this.guest = guest;
	}



	public PropsAd getProp() {
		return prop;
	}



	public void setProp(PropsAd prop) {
		this.prop = prop;
	}
	
	
	
    
}

