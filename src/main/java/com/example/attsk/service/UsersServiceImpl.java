package com.example.attsk.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.attsk.dao.*;
import com.example.attsk.model.*;

@Service
public class UsersServiceImpl {
	
	@Autowired
	private IUsersDao userDaoRef;

	
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userDaoRef.findAll();
	}

	
	public UsersDto getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDaoRef.findById(userId)
				.orElse(null);
	}


	public UsersDto createNewUser(UsersDto users) {
		// TODO Auto-generated method stub
		return userDaoRef.save(users);
	}


	public void deleteUser(UsersDto users) {
		// TODO Auto-generated method stub
		userDaoRef.delete(users);
	}



	

}
