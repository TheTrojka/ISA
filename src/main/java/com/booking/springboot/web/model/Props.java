package com.booking.springboot.web.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "props")
public class Props {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	@Column(unique = false, nullable = false)
	private String description;
	@Column(unique = false, nullable = false)
	private int price;
	private boolean active;
	
	
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    @JoinColumn(name="fanzone_id", nullable=false)
	private FanZone fanzone;
	*/
	public Props() {
		
		
	}
	public Props(int id, String email, String description, int price) {
		super();
		this.id = id;
		this.name =name ;
		this.description = description;
		this.price = price;
		
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	/*
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public FanZone getFanzone() {
		return fanzone;
	}
	public void setFanzone(FanZone fanzone) {
		this.fanzone = fanzone;
	}
*/
}
