package com.booking.springboot.web.controller.student1;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.model.Status;
import com.booking.springboot.web.service.EstablishmentService;
import com.booking.springboot.web.service.StatusService;
import com.booking.springboot.web.service.student1.FriendshipService;
import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Friendship;
import com.booking.springboot.web.users.student1.Guest;

@RestController
@RequestMapping("/guests")
@CrossOrigin(origins = "*")
public class GuestController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private GuestService guestService;

	@Autowired
	private EstablishmentService estService;

	@Autowired
	private FriendshipService frService;
	
	@Autowired
	private StatusService stService;

	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Guest> getAllGuests() {
		return guestService.getAll();

	}

	@RequestMapping("/{id}")
	public ResponseEntity<Guest> getById(@PathVariable int id) {
		Guest guest = guestService.getOneById(id);
		if (guest == null) {
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/register", method = RequestMethod.POST, consumes =
	 * MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Guest> register(Guest
	 * guest) { Guest g = guestService.addNew(guest); if (g == null) { return new
	 * ResponseEntity<Guest>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<Guest>(g, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> login(@RequestBody Guest guest) {
		Guest g = guestService.login(guest);
		if (g == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Guest>(g, HttpStatus.OK);
	}

	@RequestMapping(value = "/{guest}/checkPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> checkPassword(@PathVariable int guest, @RequestBody String password) {
		Guest g = guestService.checkPassword(password, guest);
		if (g == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Guest>(g, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> deleteGuest(@PathVariable int guest) {
		guestService.deleteGuest(guest);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> edit(@RequestBody Guest guest) {
		Guest userExists = guestService.findByEmail(guest.getEmail());
		Guest g = guestService.getOneById(guest.getId());
		if (userExists != null && g != userExists) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (guest.getConfirmationToken() == null) {
			guest.setPassword(bCryptPasswordEncoder.encode(guest.getPassword()));
			guest.setConfirmationToken(UUID.randomUUID().toString());
			guest.setEnabled(true);
			guest.setEstablishment(guestService.getOneById(guest.getId()).getEstablishment());
		}				
		if (guest.getCity() != null) {
			g.setCity(guest.getCity());
		}
		if (guest.getName() != null) {
			g.setName(guest.getName());
		}
		if (guest.getLastname() != null) {
			g.setLastname(guest.getLastname());
		}
		if (guest.getPhone() != null) {
			g.setPhone(guest.getPhone());
		}
		if (guest.getPassword() != null) {
			g.setPassword(bCryptPasswordEncoder.encode(guest.getPassword()));
		}
		Guest returnGuest = guestService.editGuest(g);
		return new ResponseEntity<Guest>(returnGuest, HttpStatus.OK);
	}

	@RequestMapping(value = "/{est}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> create(@RequestBody Guest guest, @PathVariable int est) {
		Guest userExists = guestService.findByEmail(guest.getEmail());
		if (userExists != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (est != 0) {
			System.out.println(est);
			guest.setEstablishment(estService.getOneById(est));
		}
		if (guest.getPassword() != null) {
			guest.setPassword(bCryptPasswordEncoder.encode(guest.getPassword()));
		}
		Guest g = guestService.addNew(guest);
		return new ResponseEntity<Guest>(g, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
	public ArrayList<Guest> getAllFriends(@PathVariable int id) {
		ArrayList<Friendship> allFriends = frService.getAll();
		ArrayList<Guest> friends = new ArrayList<Guest>();
		for (Friendship f : allFriends) {
			if (f.isAccepted()) {
				if (id == f.getFirst()) {
					friends.add(guestService.getOneById(f.getSecond()));
				} else if (id == f.getSecond()) {
					friends.add(guestService.getOneById(f.getFirst()));
				}
			}
		}
		return friends;
	}

	@RequestMapping(value = "/{id}/friends/available", method = RequestMethod.GET)
	public ArrayList<Guest> availableFriends(@PathVariable int id) {
		ArrayList<Friendship> allFriends = frService.getAll();
		ArrayList<Guest> friends = guestService.getAll();
		for (Friendship f : allFriends) {
			if (id == f.getFirst()) {
				friends.remove(guestService.getOneById(f.getSecond()));
			} else if (id == f.getSecond()) {
				friends.remove(guestService.getOneById(f.getFirst()));
			}
		}
		return friends;

	}

	@RequestMapping(value = "/{id}/requests", method = RequestMethod.GET)
	public ArrayList<Guest> getAllRequests(@PathVariable int id) {
		ArrayList<Friendship> allFriends = frService.getAll();
		ArrayList<Guest> friends = new ArrayList<Guest>();
		for (Friendship f : allFriends) {
			if (!f.isAccepted()) {
				if (id == f.getSecond()) {
					friends.add(guestService.getOneById(f.getFirst()));
				}
			}
		}
		return friends;
	}

	@RequestMapping(value = "/{id}/friends/add/{friend}", method = RequestMethod.POST)
	public Friendship addFriend(@PathVariable int id, @PathVariable int friend) {
		Friendship friendship = new Friendship();
		friendship.setFirst(id);
		friendship.setSecond(friend);
		friendship.setAccepted(false);
		frService.addNew(friendship);
		return friendship;
	}

	@RequestMapping(value = "/{id}/friends/accept/{friend}", method = RequestMethod.POST)
	public Friendship acceptFriend(@PathVariable int id, @PathVariable int friend) {
		Friendship friendship = frService.getOneByFriends(id, friend);
		friendship.setAccepted(true);
		frService.editFriendship(friendship);
		return friendship;
	}

	@RequestMapping(value = "/{id}/friends/decline/{friend}", method = RequestMethod.DELETE)
	public Friendship declineFriend(@PathVariable int id, @PathVariable int friend) {
		Friendship friendship = frService.getOneByFriends(id, friend);
		frService.deleteFriendship(friendship.getId());
		return friendship;
	}
	
	@RequestMapping(value = "/{id}/friends/delete/{friend}", method = RequestMethod.DELETE)
	public Friendship deleteFriend(@PathVariable int id, @PathVariable int friend) {
		Friendship friendship = new Friendship();
		if(frService.getOneByFriends(id, friend) != null) 
		{
			friendship = frService.getOneByFriends(id, friend);
		}
		else
		{
			friendship = frService.getOneByFriends(friend, id);
		}		
		frService.deleteFriendship(friendship.getId());
		return friendship;
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Status getStatus() {
		Status status = stService.getOneById(1);
		if (status == null)
		{
			status = new Status();
			status.setSilver(1);
			status.setGold(2);
			Status returnStatus = stService.addNew(status);
			return returnStatus;
		}
		else
		{
			return status;
		}		
	}
	
	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public Status getStatus(@RequestBody Status status) {
		Status old = stService.getOneById(1);
		old.setSilver(status.getSilver());
		old.setGold(status.getGold());
		Status newStatus = stService.editstatus(old);
		return newStatus;				
	}

}
