package com.booking.springboot.web.repository.student1;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.entities.student1.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
	
public ArrayList<Reservation> findByMGuest_Id(int guestId);
	
public Reservation findByMGuest_IdAndId(int guestId, int id);

public Reservation findByTiming_IdAndSeat(int timingId, int seat);
	
}
