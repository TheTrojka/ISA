package com.booking.springboot.web.controller.student1;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booking.springboot.web.users.student1.Guest;
import com.booking.springboot.web.model.Happening;
import com.booking.springboot.web.service.EmailService;
import com.booking.springboot.web.service.student1.GuestService;

@Controller
public class RegisterController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private GuestService userService;
	private EmailService emailService;
 
    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, GuestService userService, EmailService emailService) {
      
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.userService = userService;
      this.emailService = emailService;
    }
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Guest> processRegistrationForm(@RequestBody Guest guest) {
				
		// Lookup user in database by e-mail
		Guest userExists = userService.findByEmail(guest.getEmail());
		
		
		if (userExists != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			 else { // new user so we create user and send confirmation e-mail
					
			// Disable user until they click on confirmation link in email
		    guest.setEnabled(false);
		      
		    // Generate random 36-character string token for confirmation link
		    guest.setConfirmationToken(UUID.randomUUID().toString());
		    
		    userService.addNew(guest);
				
			//String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo("arminaa78@gmail.com");
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ "http://localhost:4200" + "/confirm/" + guest.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			
			/*modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");*/
		}
			
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}
	
	// Process confirmation link
	/*@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		Guest user = userService.findByConfirmationToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}*/
	
	// Process confirmation link
	@RequestMapping(value="/confirm/{token}", method = RequestMethod.GET)
	public ResponseEntity<Guest> processConfirmationForm(@PathVariable String token) {
		/*modelAndView.setViewName("confirm");
		
		Zxcvbn passwordCheck = new Zxcvbn();
		
		Strength strength = passwordCheck.measure(requestParams.get("password"));
		
		if (strength.getScore() < 3) {
			bindingResult.reject("password");
			
			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			System.out.println(requestParams.get("token"));
			return modelAndView;
		}
	*/
		// Find the user associated with the reset token
		Guest user = userService.findByConfirmationToken(token);
		// Set new password
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		// Set user to enabled
		user.setEnabled(true);
		
		// Save user
		userService.addNew(user);
		
		return new ResponseEntity<Guest>(user, HttpStatus.OK);		
	}
	
}
