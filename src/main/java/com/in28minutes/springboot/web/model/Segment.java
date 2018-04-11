package com.in28minutes.springboot.web.model;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Segment implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	private String name;	
	
	@ManyToOne @NotNull 
	@JoinColumn(name="establishment_id", nullable=false)
	@JsonBackReference // "back" part of reference: it will be omitted from serialization	
    private Establishment establishment;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fanzone") 
	@JsonManagedReference
	private Set<Seat> seats = new HashSet<Seat>();
	
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

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	
	



	
	
	
}
