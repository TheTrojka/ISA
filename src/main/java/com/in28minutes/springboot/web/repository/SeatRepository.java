package com.in28minutes.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.in28minutes.springboot.web.model.Seat;
import com.in28minutes.springboot.web.model.Segment;

public interface SeatRepository extends CrudRepository<Seat, Integer>{

	public ArrayList<Seat> findBySegment_IdAndEstablishment_Id(int segmentId, int establishmentId); // jpa sam izvali sta se trazi iz naziva metode: select s from segment where maticniRestoran=?
	public Seat findBySegment_IdAndEstablishment_IdAndId(int segmentId, int establishmentId, int id);
}
