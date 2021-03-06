package com.booking.springboot.web.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Lazy;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Segment implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;	
	
	@ManyToOne @NotNull 
	@JoinColumn(name="hall_id", nullable=false)
	@JsonBackReference (value="segment-hall")// "back" part of reference: it will be omitted from serialization	
    private Hall hall;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "segment") 
	@JsonManagedReference(value="seat-segment")
	private Set<Seat> seats = new HashSet<Seat>();
	
	private boolean active;
	
	public Segment() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	



	
	
	
}
