package com.booking.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Booked;

public interface BookedRepository extends CrudRepository<Booked, Integer>{

	Booked getOneBySeat_IdAndTiming_Id(int seatId, int timingId);

}

