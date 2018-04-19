package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.UsedProps;

public interface UsedPropsRepo extends CrudRepository<UsedProps, Integer> {
       
	public UsedProps findById(int id);
}
