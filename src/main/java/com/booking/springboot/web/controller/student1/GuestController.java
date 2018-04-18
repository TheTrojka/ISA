package com.booking.springboot.web.controller.student1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.springboot.web.service.student1.GuestService;
import com.booking.springboot.web.users.student1.Guest;

@RestController
@RequestMapping("/guests")
public class GuestController {
	

@Autowired
private GuestService guestService;

@RequestMapping(method = RequestMethod.GET)	
public ArrayList<Guest> getAllGuests(){
	return guestService.getAll();
	
}
@RequestMapping("/{id}")
public ResponseEntity<Guest> getById(@PathVariable int id) {
	Guest guest = guestService.getOneById(id);
	if(guest == null) {
		return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<Guest>(guest, HttpStatus.OK);
}
@RequestMapping(value = "/register",
method = RequestMethod.POST,
consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Guest> register(Guest guest) {
Guest g = guestService.addNew(guest);
if (g == null) {
return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
}
return new ResponseEntity<Guest>(g, HttpStatus.OK);
}


@RequestMapping(value = "/login",
method = RequestMethod.POST)
public ResponseEntity<Guest> login(Guest guest) {
Guest g = guestService.login(guest);
if (g == null){
return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
}
return new ResponseEntity<Guest>(g, HttpStatus.OK);
}

@RequestMapping(value = "/{id}",
method = RequestMethod.DELETE,
consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Guest> deleteGuest(@PathVariable int guest){
guestService.deleteGuest(guest);
return new ResponseEntity<>(HttpStatus.OK);		
}

@RequestMapping(value = "/{id}",
method = RequestMethod.PUT,
consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Guest> edit(Guest guest) {
Guest g = guestService.editGuest(guest);

return new ResponseEntity<Guest>(g, HttpStatus.OK);
}

}
