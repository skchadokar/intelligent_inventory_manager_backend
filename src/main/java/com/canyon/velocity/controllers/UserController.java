package com.canyon.velocity.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canyon.velocity.models.User;
import com.canyon.velocity.service.interfaces.IUserService;

@RestController
@CrossOrigin
@RequestMapping("/velocity")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private String TAG_NAME = "UserController";

	@Autowired
	IUserService iUserService;

	@GetMapping("/users")
	public List<User> getUsers() {
		LOGGER.info(TAG_NAME + " :: inside getUsers : ");
		// TODO Auto-generated method stub
		return iUserService.getUsers();
	}

	@PostMapping("/users")
	public Boolean createUsers(@Valid @RequestBody User aUser) {
		// TODO Auto-generated method stub
		LOGGER.info(TAG_NAME + " :: inside createUsers : User :: "+ aUser.toString());
		
		return iUserService.createUsers(aUser);
	}

	@GetMapping("/users/{aUserId}")
	public User getUserById(@PathVariable String aUserId) {
		// TODO Auto-generated method stub
		LOGGER.info(TAG_NAME + " :: inside getUserById : userid :: "+ aUserId);
		return iUserService.getUserById(aUserId);
	}

}
