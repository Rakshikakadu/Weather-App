package com.diceTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diceTech.Config.SecurityConstants;
import com.diceTech.Repositoy.CustomerRepository;
import com.diceTech.model.Customer;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth ,HttpServletResponse response){
		
		
		 Customer customer= customerRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		 System.out.println(customer);
		 System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		 return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		
		
	}
	
}
