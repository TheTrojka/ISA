package com.booking.springboot.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hall {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToOne @NotNull 
	@JoinColumn(name="establishment_id", nullable=false)
	@JsonBackReference (value="hall-establishment")// "back" part of reference: it will be omitted from serialization	
    private Establishment establishment;
	
	@OneToMany (cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "hall") 
	@JsonManagedReference(value="segment-hall")
	private Set<Segment> segments = new HashSet<Segment>();
	
	private boolean active;
	
	public Hall() {
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

	public Set<Segment> getSegments() {
		return segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
