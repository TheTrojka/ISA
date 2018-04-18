package com.booking.springboot.web.users.student1;

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
import javax.persistence.Table;

import com.booking.springboot.web.entities.student1.Reservation;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "guests")
public class Guest {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(unique=true,nullable= false)
private String email;
@Column(unique=false,nullable= false)
private String password;
@Column(unique=false,nullable= false)
private String name;
@Column(unique=false,nullable= false)
private String lastname;
@Column(unique=false,nullable=false)
private int phone;
//@OneToMany
//@JsonManagedReference
//private Set<Guest> friends = new HashSet<Guest>();

@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mGuest") 
@JsonManagedReference
private Set<Reservation> reservations = new HashSet<Reservation>();

public Set<Reservation> getReservations() {
	return reservations;
}

public void setReservations(Set<Reservation> reservations) {
	this.reservations = reservations;
}

Guest() {
	
	// TODO Auto-generated constructor stub
}

public Guest(Guest guest) {
	super();
	this.id = guest.getId();
	this.email = guest.getEmail();
	this.password = guest.getPassword();
	this.name = guest.getName();
	this.lastname = guest.getLastname();
	this.reservations = guest.getReservations();
	
}
public Guest(int id, String email, String password, String name, String lastname, int phone, HashSet<Reservation> reservations) {
	super();
	this.id = id;
	this.email = email;
	this.password = password;
	this.name = name;
	this.lastname = lastname;
	this.phone = phone;
	this.reservations = reservations;
}
public Guest(String email, String password, String ime, String prezime,int phone) {
	super();
	this.email = email;
	this.password = password;
	this.name = ime;
	this.lastname = prezime;
	this.phone = phone;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

//public Set<Guest> getFriends() {
//	return friends;
//}
//public void setFriends(Set<Guest> friends) {
//	this.friends = friends;
//}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public double getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}

}
