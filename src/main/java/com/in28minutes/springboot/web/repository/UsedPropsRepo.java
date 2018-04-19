package com.in28minutes.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.in28minutes.springboot.web.model.UsedProps;

public interface UsedPropsRepo extends CrudRepository<UsedProps, Integer> {
       
	public UsedProps findById(int id);
}
