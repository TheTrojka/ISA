package com.booking.springboot.web.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Establishment {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true,nullable= false)
	private String name;
	@Column(unique=false,nullable= false)
	private String address;
	@OneToMany(mappedBy="establishment")
    private Set<Happening> happenings;
	@OneToMany(mappedBy="establishment")
    private Set<Hall> halls;
	private Boolean theater;
	@Column(unique=false,nullable= false)
	private String description;
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "establishment") 
	@JsonManagedReference(value="establishment-admin")
    private Set<Guest> admins;
	@OneToMany
    private Set<Rating> ratings;
	private double rating;
	private boolean active;
	
	
	public Establishment() {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Happening> getHappenings() {
		return happenings;
	}
	public void setHappenings(Set<Happening> happenings) {
		this.happenings = happenings;
	}
	
	public Set<Hall> getHalls() {
		return halls;
	}

	public void setHalls(Set<Hall> halls) {
		this.halls = halls;
	}

	public Establishment(String name, String address){
		this.name = name;
		this.address = address;
	}

	public boolean isTheater() {
		return theater;
	}

	public void setTheater(boolean theater) {
		this.theater = theater;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Guest> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Guest> admins) {
		this.admins = admins;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
