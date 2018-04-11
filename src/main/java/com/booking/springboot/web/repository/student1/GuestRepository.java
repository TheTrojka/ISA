package com.booking.springboot.web.repository.student1;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.users.student1.Guest;

public interface GuestRepository extends CrudRepository<Guest,Integer> {
	public Guest findByEmail(String email);
	public ArrayList<Guest> findById(int id);

}
