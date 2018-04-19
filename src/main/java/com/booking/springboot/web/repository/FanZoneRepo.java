package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.springboot.web.model.FanZone;

@Repository
public interface FanZoneRepo extends CrudRepository<FanZone, Integer> {
	
	public FanZone findById(int id);

}
