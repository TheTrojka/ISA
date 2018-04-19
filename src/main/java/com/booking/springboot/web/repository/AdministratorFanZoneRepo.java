package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.users.model.AdministratorFanZone;

public interface AdministratorFanZoneRepo extends CrudRepository<AdministratorFanZone, Integer> {
                
	public AdministratorFanZone findById(int id);
	public void deleteById(int id);
}
