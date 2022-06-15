package com.trigun.sm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trigun.sm.models.User;
import com.trigun.sm.repo.IUserRepo;
import com.trigun.sm.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private String TAG_NAME = "UserService";

	@Autowired
	IUserRepo iUserRepo;

	@Override
	public List<User> getUsers() {
		LOGGER.info(TAG_NAME + " :: inside getUsers : ");
		// TODO Auto-generated method stub
		return (List<User>) iUserRepo.findAll();
	}

	@Override
	public User getUserById(String aUserId) {
		LOGGER.info(TAG_NAME + " :: inside getUserById : username :: "+ aUserId);
		// TODO Auto-generated method stub
		return iUserRepo.findByUserId(aUserId);
	}

	@Override
	public Boolean createUsers(User aUser) {
		LOGGER.info(TAG_NAME + " :: inside createUsers : aUser :: "+ aUser.toString());
		// TODO Auto-generated method stub
		try {
			iUserRepo.save(aUser);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(TAG_NAME + " :: inside createUsers : error :: "+ e.getMessage());
			return false;
		}
		return true;
	}

}
