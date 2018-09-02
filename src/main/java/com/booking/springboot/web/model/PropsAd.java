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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.booking.springboot.web.users.student1.Guest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "propsAd")
public class PropsAd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// @OneToOne
//	@JoinColumn(name = "usedprops_id")
//	private UsedProps usedProps;
	
	@Column(unique = false, nullable = false)
	private String name;
	@Column(unique = false, nullable = false)
	private String description;
	@Lob
	@Column( length = 100000 )
	private String picture;
	
	@Column(unique = true, nullable = true)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;
	
	private boolean accepted;
	
	private boolean reviewed;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prop") 
	@JsonManagedReference(value="prop-offer")
	private Set<Offer> offers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value="guest-ad")
    private Guest guest;
	/*
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fanzone_id", nullable=false)
	@JsonBackReference
	private FanZone fanzone;
*/
	//@ManyToOne 
	//private User user;
    public PropsAd() {
		
		
	  }
	
	public PropsAd(int id, String name, String description, int price, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		//this.usedProps =usedProps ;
		this.date = date;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

/*
	public FanZone getFanzone() {
		return fanzone;
	}

	public void setFanzone(FanZone fanzone) {
		this.fanzone = fanzone;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/*
	public UsedProps getUsedProps() {
		return usedProps;
	}

	public void setUsedProps(UsedProps usedProps) {
		this.usedProps = usedProps;
	}
*/
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}
	
	
	
	
	

}
