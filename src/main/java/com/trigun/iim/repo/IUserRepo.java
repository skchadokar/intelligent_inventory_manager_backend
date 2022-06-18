package com.trigun.iim.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trigun.iim.models.User;

@Repository
public interface IUserRepo extends CrudRepository<User, Integer> {

	User findByUserId(String username);

	
}
