package com.trigun.sm.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trigun.sm.models.User;

@Repository
public interface IUserRepo extends CrudRepository<User, Integer> {

	User findByUserId(String username);

	
}
