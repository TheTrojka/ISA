package com.booking.springboot.web.service.student1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.springboot.web.users.student1.Friendship;
import com.booking.springboot.web.model.Offer;
import com.booking.springboot.web.repository.student1.FriendshipRepository;

@Service
public class FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepo;
	
	public FriendshipService() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Friendship> getAll(){
		ArrayList<Friendship> friendships = new ArrayList<>();
		friendshipRepo.findAll().forEach(friendships::add);
		return friendships;
	}	
	
	public Friendship getOneById(int id){
		return friendshipRepo.findOne(id);
	}
	
	public Friendship getOneByFriends(int first, int second){
		return friendshipRepo.findOneByFirstAndSecond(first, second);
	}
	
	public Friendship addNew(Friendship sto){
		return friendshipRepo.save(sto);
	}
	
	public Friendship editFriendship(Friendship sto){
		return friendshipRepo.save(sto);
	}
	
	public void deleteFriendship(int id){
		friendshipRepo.delete(id);
	}
}

