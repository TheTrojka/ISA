package com.in28minutes.springboot.web.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Establishment {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String address;
	@OneToMany(mappedBy="establishment")
    private Set<Happening> happenings;
	@OneToMany(mappedBy="establishment")
    private Set<Segment> segments;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Happening> getHappenings() {
		return happenings;
	}
	public void setHappenings(Set<Happening> happenings) {
		this.happenings = happenings;
	}
	public Set<Segment> getSegments() {
		return segments;
	}
	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	
	
	
	

}
