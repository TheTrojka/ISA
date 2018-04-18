package com.booking.springboot.web.service.student1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.entities.student1.Reservation;
import com.booking.springboot.web.repository.student1.ReservationRepository;



@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	public ReservationService() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Reservation> getAllByGuest(int guestID){
		ArrayList<Reservation> reservl = new ArrayList<>();
		reservationRepository.findByMGuest_Id(guestID).forEach(reservl::add);
		return reservl;
	}
	
	public Reservation getOneById(int guestID, int id){
		Reservation r = reservationRepository.findByMGuest_IdAndId(guestID, id);
		return r;
	}
	
	public Reservation addNew(Reservation reservation){
		return reservationRepository.save(reservation);
	}
	
	public Reservation editReservation(Reservation reservation){
		return reservationRepository.save(reservation);
	}
	
	public void deleteReservation(int id){
		reservationRepository.delete(id);
	}

}
