package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.springboot.web.model.Props;

@Repository
public interface PropsRepo extends CrudRepository<Props, Integer> {
	
	public Props findById(int id);

}
