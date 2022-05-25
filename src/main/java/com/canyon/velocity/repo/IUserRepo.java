package com.canyon.velocity.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.canyon.velocity.models.User;

@Repository
public interface IUserRepo extends CrudRepository<User, Integer> {

	User findByUserId(String username);

	
}
