package com.booking.springboot.web.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.booking.springboot.web.entities.student1.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Timing {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = false, nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
	private Date time;
	
	@ManyToMany
    private Set<Segment> segments;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "timing") 
	@JsonManagedReference(value="timing-reservation")
    private Set<Reservation> reservations;
		
	public Timing() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<Segment> getSegment() {
		return segments;
	}

	public void setSegment(Set<Segment> segments) {
		this.segments = segments;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

		
	
	
}

