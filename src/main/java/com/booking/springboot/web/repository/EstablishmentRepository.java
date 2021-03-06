package com.booking.springboot.web.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.springboot.web.model.Establishment;
@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment,Integer>{

	Establishment findOneByName(String name);

}
