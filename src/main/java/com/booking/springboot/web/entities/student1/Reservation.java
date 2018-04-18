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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.booking.springboot.web.model.Happening;
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
	@Column(unique = false, nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy - HH:mm")
	private Date time;

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "matR") 
	@JsonManagedReference
	private Set<Happening> happenings = new HashSet<Happening>();

	@OneToOne @NotNull
	@JsonBackReference
	private Guest mGuest;
	
	
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

	public Reservation(int id, Date time, Set<Happening> happenings) {
		super();
		this.id = id;
		this.time = time;
		this.happenings = happenings;
	}
	public Reservation(Reservation r) {
		
		this.id = r.getId();
		this.time = r.getTime();
		this.happenings = r.getHappenings();
	
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

	public Set<Happening> getHappenings() {
		return happenings;
	}

	public void setHappenings(Set<Happening> happenings) {
		this.happenings = happenings;
	}

	
	
}
