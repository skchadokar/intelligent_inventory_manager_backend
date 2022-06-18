package com.trigun.iim.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trigun.iim.models.User;

@Service
public interface IUserService {

	List<User> getUsers();
	
	User getUserById(String aUserId);

	Boolean createUsers(User aUser);

	
	
}
