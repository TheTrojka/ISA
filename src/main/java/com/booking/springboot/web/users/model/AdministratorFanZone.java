package com.booking.springboot.web.users.model;

import javax.persistence.*;

import com.in28minutes.springboot.web.model.FanZone;

@Entity
@Table(name = "admins_fanzone")
public class AdministratorFanZone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fanzone_id", nullable=false)
	private FanZone fanzone;
	
	public AdministratorFanZone() {
		
	}
	
	public AdministratorFanZone(int id, String email, String password, String name) {
		super();
		this.id = id;
		this.name =name ;
		this.setEmail(email);
		this.setPassword(password);
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
	
	

}
