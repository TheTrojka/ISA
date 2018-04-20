package com.booking.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Discounted {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne @NotNull
	@JsonBackReference
	private Booked bookedId;
	@Min(0)
    @Max(100)
	private int discountPercentage;
	@Column(name = "IS_TAKEN", columnDefinition = "boolean default false", nullable = false)
	private Boolean taken = false;
	
	
	public Discounted() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Booked getBookedId() {
		return bookedId;
	}

	public void setBookedId(Booked bookedId) {
		this.bookedId = bookedId;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Boolean getTaken() {
		return taken;
	}

	public void setTaken(Boolean theater) {
		this.taken = theater;
	}

	
	
	
	
	
}
