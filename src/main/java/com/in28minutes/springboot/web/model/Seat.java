package com.in28minutes.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Seat {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne @NotNull 
	@JoinColumn(name="establishment_id", nullable=false)
	@JsonBackReference // "back" part of reference: it will be omitted from serialization	
    private Establishment establishment;
	
	@ManyToOne @NotNull 
	@JoinColumn(name="segment_id", nullable=false)
	@JsonBackReference // "back" part of reference: it will be omitted from serialization	
    private Segment segment;
	
	/*
	@ManyToOne(optional = false)
	// @NotNull ne mora biti rezervisan
	@JoinColumn(name = "maticni_gost_id", referencedColumnName = "id")
	@JsonBackReference
	private Gost matGost;
	*/
	
	
	public Seat() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	
	
	
}
