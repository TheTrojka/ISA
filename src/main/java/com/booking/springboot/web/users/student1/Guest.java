package com.booking.springboot.web.users.student1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.booking.springboot.web.entities.student1.Reservation;
import com.booking.springboot.web.model.Establishment;
import com.booking.springboot.web.model.Offer;
import com.booking.springboot.web.model.Props;
import com.booking.springboot.web.model.PropsAd;
import com.booking.springboot.web.model.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
private String phone;
private String city;
public enum Role {
    user,
    admin,
    establishmentAdmin,
    fanZoneAdmin
}
@Enumerated(EnumType.STRING)
private Role role;
private int resNum;

@ManyToOne(fetch = FetchType.LAZY)
@JsonBackReference(value="establishment-admin")
@JoinColumn(name="establishment_id")
private Establishment establishment;
@OneToMany
private Set<Props> props;
@OneToMany
private Set<PropsAd> propsAd;
//@OneToMany
//@JsonManagedReference
//private Set<Guest> friends = new HashSet<Guest>();

@Column(name = "enabled")
private boolean enabled;

@Column(name = "confirmation_token")
private String confirmationToken;

@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mGuest") 
@JsonManagedReference
private Set<Reservation> reservations = new HashSet<Reservation>();

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guest") 
@JsonManagedReference(value="guest-rating")
private Set<Rating> ratings;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guest") 
@JsonManagedReference(value="guest-offer")
private Set<Offer> offers;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guest") 
@JsonManagedReference(value="guest-ad")
private Set<PropsAd> ads;

public Set<Reservation> getReservations() {
	return reservations;
}

public void setReservations(Set<Reservation> reservations) {
	this.reservations = reservations;
}

public Guest() {	
	// TODO Auto-generated constructor stub
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
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}
@JsonIgnore
public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getConfirmationToken() {
	return confirmationToken;
}

public void setConfirmationToken(String confirmationToken) {
	this.confirmationToken = confirmationToken;
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public Establishment getEstablishment() {
	return establishment;
}

public void setEstablishment(Establishment establishment) {
	this.establishment = establishment;
}

public Set<Rating> getRatings() {
	return ratings;
}

public void setRatings(Set<Rating> ratings) {
	this.ratings = ratings;
}

public Set<Props> getProps() {
	return props;
}

public void setProps(Set<Props> props) {
	this.props = props;
}

public Set<PropsAd> getPropsAd() {
	return propsAd;
}

public void setPropsAd(Set<PropsAd> propsAd) {
	this.propsAd = propsAd;
}

public Set<Offer> getOffers() {
	return offers;
}

public void setOffers(Set<Offer> offers) {
	this.offers = offers;
}

public Set<PropsAd> getAds() {
	return ads;
}

public void setAds(Set<PropsAd> ads) {
	this.ads = ads;
}

public int getResNum() {
	return resNum;
}

public void setResNum(int resNum) {
	this.resNum = resNum;
}













}
