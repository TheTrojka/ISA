package com.booking.springboot.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.booking.springboot.web.users.model.AdministratorFanZone;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="fanzone")
public class FanZone {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	/*
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fanzone") 
	@JsonManagedReference
	private Set<PropsAd> propsAd = new HashSet<PropsAd>();
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fanzone") 
	@JsonManagedReference
	private Set<Props> props = new HashSet<Props>();
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fanzone") 
	@JsonManagedReference
	private Set<AdministratorFanZone> adminiFanzone = new HashSet<AdministratorFanZone>();
*/
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
/*
	public Set<PropsAd> getPropsAd() {
		return propsAd;
	}

	public void setPropsAd(Set<PropsAd> propsAd) {
		this.propsAd = propsAd;
	}

	public Set<Props> getProps() {
		return props;
	}

	public void setProps(Set<Props> props) {
		this.props = props;
	}

	public Set<AdministratorFanZone> getAdminiFanzone() {
		return adminiFanzone;
	}

	public void setAdminiFanzone(Set<AdministratorFanZone> adminiFanzone) {
		this.adminiFanzone = adminiFanzone;
	}
	*/
}
