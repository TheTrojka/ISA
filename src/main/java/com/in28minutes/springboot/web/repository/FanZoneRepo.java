package com.in28minutes.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.web.model.FanZone;

@Repository
public interface FanZoneRepo extends CrudRepository<FanZone, Integer> {
	
	public FanZone findById(int id);

}
