package com.in28minutes.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.web.model.PropsAd;

@Repository
public interface PropsAdRepo extends CrudRepository<PropsAd, Integer> {
	
	public PropsAd findById(int id);

}
