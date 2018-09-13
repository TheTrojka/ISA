package com.booking.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Integer>{

	public ArrayList<Segment> findByHall_Id(int hallId); // jpa sam izvali sta se trazi iz naziva metode: select s from segment where maticniRestoran=?
	public Segment findByHall_IdAndId(int hallId, int id);
	public Segment findByHall_IdAndName(int hallId, String name);
}
