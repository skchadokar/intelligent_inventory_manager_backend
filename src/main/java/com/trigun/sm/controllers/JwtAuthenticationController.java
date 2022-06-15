package com.trigun.sm.controllers;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trigun.sm.auth.model.JwtRequest;
import com.trigun.sm.auth.model.JwtResponse;
import com.trigun.sm.config.JwtTokenUtil;
import com.trigun.sm.models.User;
import com.trigun.sm.repo.IUserRepo;
import com.trigun.sm.service.JwtUserDetailsService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private static final Logger LOGGER=LoggerFactory.getLogger(JwtAuthenticationController.class);
	
	private String TAG_NAME = "JwtAuthenticationController";
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	IUserRepo iUserRepo;
	
	


	@PostMapping("/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		LOGGER.info(TAG_NAME + " :: inside generateAuthenticationToken : user name :: "+authenticationRequest.getUsername());
		Objects.requireNonNull(authenticationRequest.getUsername());
		Objects.requireNonNull(authenticationRequest.getPassword());
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse mJwtResponse = null;
		User mUser = iUserRepo.findByUserId(authenticationRequest.getUsername());
		if(mUser!=null && !mUser.getPassword().equals(authenticationRequest.getPassword())) {
			LOGGER.error(TAG_NAME + " :: inside generateAuthenticationToken : user auth fail!");
		   return new ResponseEntity<String>("Failed", HttpStatus.UNAUTHORIZED);
					
		}
		
		if (mUser != null) {
			LOGGER.error(TAG_NAME + " :: inside generateAuthenticationToken : user found!");
			mJwtResponse = new JwtResponse(token);
			mJwtResponse.setUserId(authenticationRequest.getUsername());
			mJwtResponse.setUserName(mUser.getFirstName() + " " + mUser.getLastName());
			
		}

		return ResponseEntity.ok(mJwtResponse);
	}

}
