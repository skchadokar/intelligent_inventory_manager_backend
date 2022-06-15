package com.trigun.sm.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trigun.sm.models.User;

@Service
public interface IUserService {

	List<User> getUsers();
	
	User getUserById(String aUserId);

	Boolean createUsers(User aUser);

	
	
}
