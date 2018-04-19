package com.booking.springboot.web.model;

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
public class Booked {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne @NotNull 
	@JoinColumn(name="timing_id", nullable=false)
	@JsonBackReference (value="timing-booked")// "back" part of reference: it will be omitted from serialization	
    private Timing timing;
	
	@ManyToOne @NotNull 
	@JoinColumn(name="segment_id", nullable=false)
	@JsonBackReference (value="seat-booked")// "back" part of reference: it will be omitted from serialization	
    private Seat seat;	
	
	public Booked() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timing getTiming() {
		return timing;
	}

	public void setTiming(Timing timing) {
		this.timing = timing;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	
	
	
}
