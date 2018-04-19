package com.in28minutes.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.web.model.Props;

@Repository
public interface PropsRepo extends CrudRepository<Props, Integer> {
	
	public Props findById(int id);

}
