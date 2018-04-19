package com.booking.springboot.web.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.booking.springboot.web.entities.student1.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Happening {	
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String actors;
	public enum Genre {
	    action,
	    scifi,
	    fantasy,
	    drama,
	    comedy,
	    horror
	}
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private String director;
	private int duration;
	private double price;
	private String decription;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="establishment_id", nullable=false)
    private Establishment establishment;
	@OneToMany
    private Set<Segment> segments;
	@OneToMany
    private Set<Timing> timings;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Reservation matR;
	
	public Happening() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@JsonIgnore
	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Set<Segment> getSegments() {
		return segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}

	public Set<Timing> getTimings() {
		return timings;
	}

	public void setTimings(Set<Timing> timings) {
		this.timings = timings;
	}
	
	
	
	
	
	
}
