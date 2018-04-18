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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Timing {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = false, nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference(value="segment-timing")
    @JoinColumn(name="segment_id", nullable=false)
	private Segment segment;
		
	public Timing() {
		// TODO Auto-generated constructor stub
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

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}	
	
	
}

