package com.in28minutes.springboot.web.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.web.model.Establishment;
@Repository
public interface EstablishmentRepository extends CrudRepository<Establishment,Integer>{

}
