package com.in28minutes.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.in28minutes.springboot.web.model.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Integer>{

	public ArrayList<Segment> findByEstablishment_Id(int establishmentId); // jpa sam izvali sta se trazi iz naziva metode: select s from segment where maticniRestoran=?
	public Segment findByEstablishment_IdAndId(int establishmentId, int id);
}
