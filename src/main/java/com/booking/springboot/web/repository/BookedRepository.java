package com.booking.springboot.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.model.Booked;

public interface BookedRepository extends CrudRepository<Booked, Integer>{

	Booked getOneBySeat_IdAndTiming_Id(int seatId, int timingId);

	ArrayList<Booked> getBySeat_Id(int seatId);

}

