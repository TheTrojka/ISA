package com.booking.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.springboot.web.model.Happening;



@Repository
public interface HappeningRepository extends CrudRepository<Happening,Integer>{

	public ArrayList<Happening> findByEstablishment_Id(int fakturaId);
	
	public Happening findByEstablishment_IdAndId(int fakturaId, int redni_broj);
	
	public Happening findById(int rednibroj);

	public Happening findByTitleAndEstablishment_Id(String title, int estId);

}
