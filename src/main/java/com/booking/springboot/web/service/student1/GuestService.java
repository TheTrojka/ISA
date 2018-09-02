package com.booking.springboot.web.service.student1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.repository.student1.GuestRepository;
import com.booking.springboot.web.users.student1.Guest;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	public GuestService() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Guest> getAll() {
		ArrayList<Guest> guests = new ArrayList<>();
		guestRepository.findAll().forEach(guests::add);
		return guests;

	}

	public Guest getOneById(int id) {
		return guestRepository.findOne(id);
	}

	public Guest addNew(Guest guest) {
		return guestRepository.save(guest);
	}

	public Guest editGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	public void deleteGuest(int id) {
		guestRepository.delete(id);
	}
	
	public Guest login(Guest guest) {
		Guest g = guestRepository.findByEmail(guest.getEmail());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (g != null) {
			if (encoder.matches(guest.getPassword(), g.getPassword())) {
				return g;
			}
		}
		return null;
	}
	
	public Guest checkPassword(String password, int i) {
		Guest g = guestRepository.findOne(i);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (g != null) {
			if (encoder.matches(password, g.getPassword())) {
				return g;
			}
		}
		return null;
	}
	
	public Guest findByEmail(String email) {
		return guestRepository.findByEmail(email);
	}
	
	public Guest findByConfirmationToken(String confirmationToken) {
		return guestRepository.findByConfirmationToken(confirmationToken);
	}

}
