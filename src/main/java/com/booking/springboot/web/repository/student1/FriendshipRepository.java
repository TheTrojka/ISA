package com.booking.springboot.web.repository.student1;

import org.springframework.data.repository.CrudRepository;

import com.booking.springboot.web.users.student1.Friendship;

public interface FriendshipRepository extends CrudRepository<Friendship, Integer>{

	Friendship findOneByFirstAndSecond(int first, int second);

}
