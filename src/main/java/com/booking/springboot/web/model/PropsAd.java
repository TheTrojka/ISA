package com.booking.springboot.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "propsAd")
public class PropsAd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// @OneToOne
//	@JoinColumn(name = "usedprops_id")
//	private UsedProps usedProps;
	
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String description;
	@Column(unique = true, nullable = false)
	private int price;
	
	@Column(unique = true, nullable = true)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date date;
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
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

}
