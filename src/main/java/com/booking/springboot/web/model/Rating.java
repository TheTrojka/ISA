package com.booking.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Rating {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	@Min(1)
    @Max(5)
	private int grade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value="guest-rating")
    private Guest guest;
		
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	
	
	
}


