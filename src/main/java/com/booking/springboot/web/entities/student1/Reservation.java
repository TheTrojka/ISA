package com.booking.springboot.web.entities.student1;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.model.Segment;
import com.booking.springboot.web.model.Timing;
import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;





@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value="timing-reservation")
    @JoinColumn(name="timing_id", nullable=false)
	private Timing timing;
	
	@OneToOne @NotNull
	@JsonBackReference
	private Guest mGuest;
	
	private int seat;
	
	
	public Guest getmGuest() {
		return mGuest;
	}

	public void setmGuest(Guest mGuest) {
		this.mGuest = mGuest;
	}

	public Reservation() {
		super();
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

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	


	
	
}
