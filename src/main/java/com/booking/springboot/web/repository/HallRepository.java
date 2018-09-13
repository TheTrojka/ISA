package com.booking.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Hall;

public interface HallRepository extends CrudRepository<Hall, Integer>{

	ArrayList<Hall> findByEstablishment_Id(int restoranId);

	Hall findByEstablishment_IdAndId(int restoranId, int id);

	Hall findByEstablishment_IdAndName(int establishmentId, String name);

}
