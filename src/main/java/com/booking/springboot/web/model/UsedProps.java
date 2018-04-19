package com.booking.springboot.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "usedprops")
public class UsedProps {
	
	

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = true, nullable = false)
	private String description;
	@Column(unique = true, nullable = false)
	private int price;
	@Column(unique = true, nullable = false)
	private String image;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fanzone_id", nullable=false)
	private FanZone fanzone;
	
	public UsedProps() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public UsedProps(int id, String email, String description, int price, String image) {
		super();
		this.id = id;
		this.name =name ;
		this.description = description;
		this.price = price;
		this.image = image;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



}
